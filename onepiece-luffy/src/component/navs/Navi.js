import React, {Component} from 'react';
import './Navi.css'
import {Link} from "react-router-dom";


class Navi extends Component {

    constructor(props) {
        super(props);
        this.state = {
            menu: 'home',
            exchange: ''
        }
    }

    menu = {
        'home': '홈',
        'notice': '공지사항',
        'event': '이벤트'
    };

    keys = Object.keys(this.menu);

    componentWillReceiveProps(nextProps, nextContext) {
        this.setState({
            menu: nextProps.menu
        })
    }

    onClick(menu) {
        this.setState({
                menu
            }
        )
    }

    render() {
        return (
            <div>
                <div id="navi" className="row">
                    {
                        this.keys.map(value => {
                            let className = "navi-item";

                            if (value === this.state.menu) {
                                className += '-select';
                            }
                            return <Link className={className} to={value} onClick={()=>this.onClick(value)}>{this.menu[value]}</Link>
                        })
                    }
                </div>
            </div>

        )
    }
}

export default Navi;