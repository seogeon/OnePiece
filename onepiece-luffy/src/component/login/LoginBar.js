import React, {Component} from 'react';
import './LoginBar.css'
import Image from "react-bootstrap/Image";
import Button from "react-bootstrap/Button";
import KakaoApi from "../../service/login/KakaoApi"

class LoginBar extends Component {


    loginButtonClick() {
        KakaoApi.getLoginToken();
    }
    render() {
        return (
            <div id="login-bar"><Button variant="link" id ="kakao-login-btn" onClick={()=>this.loginButtonClick()} ><Image src="/img/kakao_login_btn.png"/></Button></div>
        )
    }

}

export default LoginBar;