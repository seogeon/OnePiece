import React, {Component} from 'react';
import Table from "react-bootstrap/Table";
import Pagination from "react-bootstrap/Pagination";
import NoticeApi from "../../service/NoticeApi"
import Badge from "react-bootstrap/Badge";
import './SearchBar.css';

class SearchResult extends Component {

    pageActive = 0;

    constructor(props) {
        super(props);
        this.state = {
            data: props.data
        }
    }

    componentWillReceiveProps(nextProps, nextContext) {
        this.pageActive = 0;
        this.setState({
            data: nextProps.data
        })
    }


    onClickPageButton(page) {
        NoticeApi.searchNotice(this.props.exchange, this.props.keyword, page).then(value => {
            this.pageActive = page;

            if (value.data.code === '0') {
                this.setState({
                    data: value.data.data
                });
            }
        })
    }


    buttons = [];

    createPageButton() {

        this.buttons = [];
        this.buttons.push(<Pagination.First onClick={()=> this.onClickPageButton(0)}/>);

        if(this.state.data.total_page <= 5) {
            for (let i = 0; i < this.state.data.total_page; i++) {
                if (this.pageActive === i) {
                    this.buttons.push(<Pagination.Item key={i} active>{i + 1}</Pagination.Item>)
                } else {
                    this.buttons.push(<Pagination.Item key={i} onClick={()=> this.onClickPageButton(i)}>{i + 1}</Pagination.Item>)
                }
            }
        } else {
            if(this.pageActive <= 3) {

                for(let i = 0 ; i < 5 ; i++) {
                    if (this.pageActive === i) {
                        this.buttons.push(<Pagination.Item key={i} active>{i + 1}</Pagination.Item>)
                    } else {
                        this.buttons.push(<Pagination.Item key={i} onClick={()=> this.onClickPageButton(i)}>{i + 1}</Pagination.Item>)
                    }
                }
                this.buttons.push(<Pagination.Ellipsis />)
                this.buttons.push(<Pagination.Item key={this.state.data.total_page-1} onClick={()=> this.onClickPageButton(this.state.data.total_page-1)}>{this.state.data.total_page}</Pagination.Item>)

            } else if(this.pageActive >= this.state.data.total_page - 4) {


                this.buttons.push(<Pagination.Item key={0} onClick={()=> this.onClickPageButton(0)}>{1}</Pagination.Item>)
                this.buttons.push(<Pagination.Ellipsis />)
                for(let i =  this.state.data.total_page - 5; i <  this.state.data.total_page ; i++) {
                    if (this.pageActive === i) {
                        this.buttons.push(<Pagination.Item key={i} active>{i + 1}</Pagination.Item>)
                    } else {
                        this.buttons.push(<Pagination.Item key={i} onClick={()=> this.onClickPageButton(i)}>{i + 1}</Pagination.Item>)
                    }
                }


            } else {

                this.buttons.push(<Pagination.Item key={0} onClick={()=> this.onClickPageButton(0)}>{1}</Pagination.Item>)
                this.buttons.push(<Pagination.Ellipsis />)
                for(let i = this.pageActive - 2; i <= this.pageActive + 2 ; i++) {
                    if (this.pageActive === i) {
                        this.buttons.push(<Pagination.Item key={i} active>{i + 1}</Pagination.Item>)
                    } else {
                        this.buttons.push(<Pagination.Item key={i} onClick={()=> this.onClickPageButton(i)}>{i + 1}</Pagination.Item>)
                    }
                }
                this.buttons.push(<Pagination.Ellipsis />)
                this.buttons.push(<Pagination.Item key={this.state.data.total_page-1} onClick={()=> this.onClickPageButton(this.state.data.total_page-1)}>{this.state.data.total_page}</Pagination.Item>)

            }


        }
        this.buttons.push(<Pagination.Last onClick={()=>this.onClickPageButton(this.state.data.total_page-1)}/>);
    }

    render() {

        if (this.state.data === '0') {
            return <h1></h1>
        } else {

            let noticeList = this.state.data.notice_item_list;

            this.createPageButton();

            return (<div>
                    <Table responsive id="notice-table">
                        <thead>
                        <tr>
                            <th className="notice-exchange">거래소</th>
                            <th className="notice-title">제 목</th>
                            <th>날 짜</th>
                        </tr>
                        </thead>
                        <tbody>
                        {noticeList.map(function (object, i) {

                            let date = new Date();
                            let today = date.getFullYear() + '-' + (date.getMonth()+1) + '-' + date.getDate();

                            if(today === object.updatedAt.split("T")[0]) {
                                return (
                                    <tr key={i} className="notice-item" onClick={() => window.open(object.url)}>
                                        <td className="notice-exchange">{object.exchange}</td>
                                        <td className="notice-title">{object.title} <Badge variant="info">New</Badge></td>
                                        <td>{object.updatedAt.split("T")[0]}</td>
                                    </tr>
                                )
                            }else {
                                return (
                                    <tr key={i} className="notice-item" onClick={() => window.open(object.url)}>
                                        <td className="notice-exchange">{object.exchange}</td>
                                        <td className="notice-title">{object.title}</td>
                                        <td>{object.updatedAt.split("T")[0]}</td>
                                    </tr>
                                )
                            }
                        })}
                        </tbody>
                    </Table>
                    <div className="row">
                        <Pagination className="page-button">
                            {this.buttons}
                        </Pagination>
                    </div>
                </div>
            )

        }
    }

}

export default SearchResult;