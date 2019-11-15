import React, {Component} from 'react';
import './Navi.css'
import NoticeApi from '../../service/NoticeApi'


class Navi extends Component {

    constructor(props) {
        super(props);
        this.state = {
            menu: 'home',
            exchange: ''
        }
        this.getExchange();
    }

    menu = {
        'home': '홈',
        'news': '최신 정보',
        'airdrop': '에어드랍 이벤트'
    };

    keys = Object.keys(this.menu);

    getExchange() {
        NoticeApi.getExchangeList().then(value => {
            if (value.data.code === '0') {
                this.setState({
                    exchange: value.data.data
                });
            }
        });

    }

    componentWillReceiveProps(nextProps, nextContext) {
        this.setState({
            menu: nextProps.menu
        })
        this.getExchange();
    }

    render() {

        console.log(this.state.exchange);

        return (
            <div>
                <div id="navi" className="row">
                    {
                        this.keys.map(value => {
                            let className = "navi-item";

                            if (value === this.state.menu) {
                                className += '-select';
                            }
                            return <a className={className} href="#home">{this.menu[value]}</a>
                        })
                    }
                </div>
            </div>

        )
    }
}

export default Navi;