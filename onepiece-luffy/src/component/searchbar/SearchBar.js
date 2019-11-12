import React, {Component} from 'react';
import NoticeApi from '../../service/NoticeApi';
import SearchResult from './SearchResult'
import './SearchBar.css';
import Form from 'react-bootstrap/Form';
import Button from "react-bootstrap/Button";

class SearchBar extends Component {


    regExCheck(keyword) {
        /* eslint-disable */
        const regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-+<>@\#$%&\\\=\(\'\"]/gi;

        return regExp.test(keyword);
    }

    constructor(props) {
        super(props);
        this.state = {
            data: null,
            exchange: 'ALL',
            list: null,
            keyword: null
        }
    }

    keyword = '';

    componentWillMount() {
        this.getExchange();
    };

    change(e) {
        this.setState({exchange: e.target.value})
    }


    getExchange() {
        NoticeApi.getExchangeList().then(value => {
            if (value.data.code === '0') {
                this.setState({
                    data: value.data.data
                });
            }
        });
    }

    searchNotice(event) {

        this.keyword = event.target.value;
        if (event.keyCode === 13) {


            if(!this.regExCheck(this.keyword)) {
                NoticeApi.searchNotice(this.state.exchange, this.keyword, 0).then(value => {
                    if (value.data.code === '0') {
                        this.setState({
                            list: value.data.data
                        });

                        //    console.log(this.state.list);
                    }
                })
            }else {
                alert("특수 문자는 검색이 안됩니다.")
            }
        }
    }

    onClickButton() {

        if(!this.regExCheck(this.keyword)) {
            NoticeApi.searchNotice(this.state.exchange, this.keyword, 0).then(value => {
                if (value.data.code === '0') {
                    this.setState({
                        list: value.data.data
                    });
                    //   console.log(this.state.list);
                }
            })
        }else {
            alert("특수 문자는 검색이 안됩니다.")
        }
    }

    render() {
        const {data} = this.state;

        if (!data) {
            return <h1>Loading...</h1>
        } else {

            return (
                <div className="col-12">
                    <div className="row">
                        <Form.Group id="search-bar">
                            <Form.Control id={"exchange_list"} as="select" onChange={(value) => this.change(value)}>
                                <option key={"ALL"} value={"ALL"}>ALL</option>
                                {data.map(function (object, i) {
                                    return (
                                        <option key={object.code} value={object.code}>{object.code}</option>
                                    )
                                })}
                            </Form.Control>
                            <Form.Control id="search-key" aria-describedby="basic-addon1" placeholder="'에어드랍' 을 검색 해보세요."
                                          onKeyUp={(event) => this.searchNotice(event)}/>
                            <Button type="submit" id="search-button" variant="info"
                                    onClick={() => this.onClickButton()}>검
                                색</Button>
                        </Form.Group>
                    </div>
                    <div>
                        <SearchResult data={ this.state.list === null ? '0' : this.state.list } keyword={this.keyword} exchange={this.state.exchange}/>
                    </div>
                </div>
            )
        }
    }
}

export default SearchBar;