import React, {Component} from 'react';
import './LoginBar.css'
import Button from "react-bootstrap/Button";
import Kakao from "kakaojs";
import Login from '../../service/login/Login';
import cookie from 'react-cookies';
import Image from "react-bootstrap/Image";

class LoginBar extends Component {

    constructor(props) {
        super(props)
        this.state = {
            userId: cookie.load('userId'),
            check: false
        }
    }


    componentDidMount() {

        if(!this.state.check) {
            Kakao.init("0ed5686ee75e1839c5f1f4acc4f8b110");
            Kakao.Auth.createLoginButton({
                container: '#kakao-login-btn',
                success: function (authObj) {
                    Login.login(authObj.access_token).then(value => {
                        cookie.save(
                            'userId', value.headers["userid"], {path: '/', maxAge: 3600}
                        );
                         window.location.reload()
                    });
                },
                fail: function (err) {
                    alert("로그인에 실패하였습니다. 잠시 후 다시 시도해주세요.");
                }
            });
        }
        this.loginCheck().then();
    }

    async loginCheck() {

        let sessionId = this.state.userId;

        if(sessionId !== null && sessionId !== '' && typeof sessionId !== 'undefined') {
            // 서버로 토큰 인증 요청
            await Login.loginCheck(sessionId).then(value => {

                console.log(value);
                if(value.data.code === '0') {
                    this.setState({
                        check: true,
                        nickname: value.data.data.nickname,
                        email: value.data.data.email,
                        userId: value.data.data.id,
                        token: value.data.data.token
                    })
                }
            });
        }
    }

    async logout() {
        await Login.logout(this.state.userId).then(value => {
            cookie.remove('userId')
            this.setState({
                check: false,
                nickname: '',
                email: '',
                userId: '',
                token: ''
            })

            window.location.reload();
        });
    }


    render() {

        if(this.state.check) {
            return (<div id="login-bar">{this.state.nickname}<Button variant="link" id="logout-btn" onClick={()=>this.logout()}><Image id="logout-btn-img" src="/img/logout-btn.png"/></Button></div>);
        } else {
            return (<div id="login-bar"><Button variant="link" id="kakao-login-btn"></Button></div>);
        }
    }

}

export default LoginBar;