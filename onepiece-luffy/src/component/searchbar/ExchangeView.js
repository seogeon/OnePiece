import React, {Component} from 'react';
import Button from "react-bootstrap/Button";
import Collapse from "react-bootstrap/Collapse";

class ExchangeView extends Component {
;
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

        // const name = exchange.map(function (object, i) {
        //     return object.exchange;
        // })

        return (
            <div className="col-12">
                <Button variant="outline-info" onClick={()=> this.setState({openCheck: !this.state.openCheck})} aria-controls="example-collapse-text"
                        aria-expanded={this.state.onenCheck}>지원 되는 거래소들을 보시겠어요?</Button>
                <Collapse in={this.state.openCheck}>
                    <div id="example-collapse-text" className="col-12">
                        {exchange.map(function (object, i) {
                            return <div className="exchange-name">{object.exchange}</div>
                        })}
                    </div>
                </Collapse>
            </div>
        )
    }
}

export default ExchangeView;