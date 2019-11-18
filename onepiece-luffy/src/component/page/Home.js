import React from 'react';
import '../../App.css';
import News from '../../component/news/News';

function Home() {
    return (
        <div>
            <div className="row">
                <News/>
            </div>
        </div>
    );
}

export default Home;
