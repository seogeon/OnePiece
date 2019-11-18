import React, {Component} from 'react';
import Image from "react-bootstrap/esm/Image";
import './Header.css'
import Navi from "../navs/Navi";

class FirstHeader extends Component {

    render() {
        return (
            <div>
                <div className="row" id="first-header">
                    <div id="first-site-name"><Image id="first-page-logo" src="/img/site-color-logo.png"/> 코린이.me</div>
                </div>
                <Navi/>
            </div>
        )
    }
}

export default FirstHeader;