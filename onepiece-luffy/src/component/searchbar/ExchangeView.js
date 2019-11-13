import React, {Component} from 'react';
import Button from "react-bootstrap/Button";
import Collapse from "react-bootstrap/Collapse";
import Image from "react-bootstrap/Image";
import './SearchBar.css';


class ExchangeView extends Component {

    constructor(props) {
        super(props)
        this.state = {
            exchange: props.exchange,
            openCheck: false
        }
    }

    componentWillReceiveProps(nextProps, nextContext) {
        this.setState({
            exchange: nextProps.exchange
        })
    }

    render() {

        let exchange = this.state.exchange.data;

        return (
            <div className="col-12">
                <Button variant="outline-info" onClick={() => this.setState({openCheck: !this.state.openCheck})}
                        aria-controls="example-collapse-text"
                        aria-expanded={this.state.onenCheck}>지원 되는 거래소들을 보시겠어요?</Button>
                <Collapse in={this.state.openCheck}>
                    <div id="example-collapse-text" className="col-12">
                        {exchange.map(function (object, i) {
                            return (
                                <a href={object.url}>
                                    <Image className="exchange-logo" src={"/img/" + object.code + "-logo.png"}/>
                                </a>
                            )
                        })}
                    </div>
                </Collapse>
            </div>
        )
    }
}

export default ExchangeView;