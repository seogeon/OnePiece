import React, {Component} from 'react';
import './LoginBar.css'
import Button from "react-bootstrap/Button";
import Kakao from "kakaojs";
import NoticeApi from '../../service/notice/NoticeApi';
import cookie from 'react-cookies';

class LoginBar extends Component {

    constructor(props) {
        super(props)
        this.state = {
            sessionId: cookie.load('token')
        }
        console.log(this.state.sessionId);
    }

    componentDidMount() {
        Kakao.init("0ed5686ee75e1839c5f1f4acc4f8b110");
        Kakao.Auth.createLoginButton({
            container: '#kakao-login-btn',
            success: function (authObj) {
                 NoticeApi.authentication(authObj.access_token).then(value => {
                     cookie.save(
                         'token', value.headers["token"], {path: '/'}
                     )
                });
            },
            fail: function (err) {
                alert("로그인에 실패하였습니다.");
            }
        });
    }

    loginCheck() {

        let sessionId = this.state.sessionId;

        if(sessionId !== null && sessionId !== '') {
            // 서버로 토큰 인증 요청
            NoticeApi.authentication(sessionId).then(value => {

                console.log(value);

                if(value.data.code == '0') {
                    cookie.save('token', value.headers["token"], {path: '/'})
                    alert("로그인에 성공하였습니다.");
                    return true;
                } else {
                    alert("로그인에 실패하였습니다.");
                }
            });
        }

        return false;
    }

    loginBarCreate() {

        if(this.loginCheck()) {
            return (
                <div id="login-bar">로그인 성공</div>
            )
        } else {
            return (
                <div id="login-bar"><Button variant="link" id="kakao-login-btn"></Button></div>
            )
        }
    }

    render() {

        this.loginCheck();
        return (
            <div id="login-bar"><Button variant="link" id="kakao-login-btn"></Button></div>
        )
    }

}

export default LoginBar;