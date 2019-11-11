import React, {Component} from 'react';
import NoticeApi from "../../service/NoticeApi";
import './SearchBar.css';
import Form from "react-bootstrap/Form"
import Button from "react-bootstrap/Button";

class SearchBar extends Component {

    constructor(props) {
        super(props);
        this.state = {
            data: null,
            exchange: 'ALL'
        }
    }

    componentWillMount() {
        this.getExchange();
    };


    getExchange() {
        NoticeApi.getExchangeList().then(value => {
            if (value.data.code === '0') {
                this.setState({
                    data: value.data.data
                });
            }
        });
    }

    render() {
        const {data} = this.state;

        if (!data) {
            return <h1>Loading...</h1>
        } else {

            return <Form.Group controlId="searchBar">
                <Form.Control id={"exchange_list"} as="select">
                    <option key={"ALL"}>ALL</option>
                    {data.map(function (object, i) {
                        return (
                            <option key={object.code}>{object.code}</option>
                        )
                    })}
                </Form.Control>
                <Form.Control aria-describedby="basic-addon1"/>
                <Button id="search-button" variant="info">검 색</Button>
            </Form.Group>
        }
    }
}

export default SearchBar;