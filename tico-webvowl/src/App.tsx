import { BrowserRouter as Router, Link, Routes , Route } from 'react-router-dom';
import Home from './views/react-ui/Home';
import WorkSpace from './views/react-ui/Workspace';
import ChooseOntology from './views/react-ui/modules/choose/ChooseOntology';
import ImportOntology from './views/react-ui/modules/importOntology/ImportOntology';
import './index.css';
import EditEvolutionaryActions from './views/react-ui/modules/importOntology/EditEvolutionaryActions';
import { ChakraProvider } from '@chakra-ui/react'
import Wrapper from './views/webvowl/Wrapper';
import EvolutionVisualization from './views/react-ui/modules/viewer/EvolutionVisualization';
import NavBar from './views/components/navbar/Navbar';
import LargeVisualization from './views/react-ui/modules/viewer/LargeVisualization';

function App() {
  
  return (
    <ChakraProvider>
      <div className="App">
        <NavBar />
        <Router>
          <div>
            <Routes >
              <Route path="/" element={<Home />} />
              <Route path="/workspace" element={<ImportOntology />}/>
              <Route path="/load" element={<ChooseOntology />}/>
              <Route path="/import" element={<ImportOntology />}/>
              <Route path="/actions" element={<EditEvolutionaryActions />}/>
              <Route path="/viewer" element={<EvolutionVisualization />}/>
              <Route path="/largeviewer" element={<LargeVisualization />}/>
            </Routes >
          </div>
        </Router>   
        {/* <iframe src='/webvowl/index.html' height={"700px"} width={"1000px"} /> */}
      </div>
    </ChakraProvider>
  );
}

export default App;
