import React, {Component} from 'react';
import Table from "react-bootstrap/Table";
import Pagination from "react-bootstrap/Pagination";
import NoticeApi from "../../service/NoticeApi"

class SearchResult extends Component {

    pageActive = 0;

    constructor(props) {
        super(props);
        this.state = {
            data: props.data
        }
    }

    componentWillReceiveProps(nextProps, nextContext) {
        this.setState({
            data: nextProps.data
        })
    }


    onClickPageButton(page) {
        console.log(page);
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
        this.buttons.push(<Pagination.First/>);

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

            } else if(this.pageActive >= this.state.data.total_page - 3) {


                this.buttons.push(<Pagination.Item key={0} onClick={()=> this.onClickPageButton(0)}>{1}</Pagination.Item>)
                this.buttons.push(<Pagination.Ellipsis />)
                for(let i =  this.state.data.total_page - 4; i <  this.state.data.total_page ; i++) {
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
        this.buttons.push(<Pagination.Last/>);
    }

    render() {
        // console.log(this.props.keyword);
        // console.log(this.props.data);
        // console.log(this.props.exchange);
        // console.log(this.props);

        console.log(this.state);

        if (this.state.data === '0') {
            return <h1>검색하세요~</h1>
        } else {

            let noticeList = this.state.data.notice_item_list;
            let totalPage = this.state.data.total_page;

            this.createPageButton();

            return (<div>
                    <Table responsive>
                        <thead>
                        <tr>
                            <th>거래소</th>
                            <th>제 목</th>
                            <th>날 짜</th>
                        </tr>
                        </thead>
                        <tbody>
                        {noticeList.map(function (object, i) {
                            return (
                                <tr key={i} className="notice-item" onClick={() => window.open(object.url)}>
                                    <td>{object.exchange}</td>
                                    <td>{object.title}</td>
                                    <td>{object.createdAt.split("T")[0]}</td>
                                </tr>
                            )
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