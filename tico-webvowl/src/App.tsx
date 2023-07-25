import { BrowserRouter as Router, Link, Routes , Route } from 'react-router-dom';
import Home from './views/react-ui/Home';
import {default as WebVOWLWrapper} from './views/webvowl/Wrapper';
import WorkSpace from './views/react-ui/Workspace';


function App() {
  
  return (
    <div className="App">
      <Router>
        <div>
          <Routes >
            <Route path="/" element={<Home />} />
            <Route path="/workspace" element={<WorkSpace />}/>
          </Routes >
        </div>
      </Router>   
    </div>
  );
}

export default App;
