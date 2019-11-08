import React, { Component } from 'react';
import './News.css';
import NoticeApi from '../../service/GetNotice'
import ListGroup from "react-bootstrap/esm/ListGroup";

class NewsBody extends Component {

    constructor(props) {
        super(props);
        this.state = {
            data: null
        }
    }

    componentWillMount() {
        this.getNotice();
    };


    getNotice() {
        NoticeApi.getNewsToOversea(this.props.oversea).then(value => {
            if(value.data.code === '0') {
                this.setState({
                    data: value.data.data.notice_item_list
                });
            }
        });
    }


    render() {
        const {data} = this.state;

        if(!data) {
            return <h1>Loading...</h1>
        } else {
            return <ListGroup>
                {data.map(function(object, i) {
                    return (
                        <ListGroup.Item className="news-list-item" key={object.id} action href={object.url} target="_blank">
                            <div className='row'>
                                <div className='col-3 news-exchange'>{object.exchange}</div>
                                <div className='col-9 news-title'>{object.title}</div>
                            </div>
                        </ListGroup.Item>
                    )
                })}
            </ListGroup>
        }
    }
}

export default NewsBody;