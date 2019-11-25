import React, {Component} from 'react';
import './LoginBar.css'
import Image from "react-bootstrap/Image";
import Button from "react-bootstrap/Button";

class LoginBar extends Component {

    api = "https://kauth.kakao.com";
    // static rediredctUri = "http://13.125.248.134:5001/oauth"
    rediredctUri = "http://localhost:5001/oauth";
    restApiKey = "ac4bfcbe80a7ff1e29e3752df45dda9b";


    url  = this.api + "/oauth/authorize?client_id=" + this.restApiKey + "&redirect_uri=" + this.rediredctUri + "&response_type=code"

    render() {
        return (
            <div id="login-bar"><Button href={this.url} variant="link" id ="kakao-login-btn"><Image src="/img/kakao_login_btn.png"/></Button></div>
        )
    }

}

export default LoginBar;