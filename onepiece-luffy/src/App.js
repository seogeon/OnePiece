import React from 'react';
import './App.css';
import News from './component/news/News';

function App() {
  return (
    <div className="App">
      <div className="container">
        <div className="row">
          <News/>
        </div>
      </div>
    </div>
  );
}

export default App;
