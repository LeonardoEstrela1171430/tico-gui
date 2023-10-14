import { BrowserRouter as Router, Link, Routes , Route } from 'react-router-dom';
import Home from './views/react-ui/Home';
import ChooseOntology from './views/react-ui/pages/choose/ChooseOntology';
import ImportOntology from './views/react-ui/pages/importOntology/ImportOntology';
import './index.css';
import EditEvolutionaryActions from './views/react-ui/pages/importOntology/EditEvolutionaryActions';
import { ChakraProvider } from '@chakra-ui/react'
import EvolutionVisualization from './views/react-ui/pages/viewer/EvolutionVisualization';
import NavBar from './views/react-ui/modules/Navbar';
import LargeVisualization from './views/react-ui/pages/viewer/LargeVisualization';

function App() {
  
  return (
    <ChakraProvider>
      <div className="App">
        <NavBar />
        <Router>
            <Routes >
              <Route path="/" element={<Home />} />
              <Route path="/workspace" element={<ImportOntology />}/>
              <Route path="/load" element={<ChooseOntology />}/>
              <Route path="/import" element={<ImportOntology />}/>
              <Route path="/actions" element={<EditEvolutionaryActions />}/>
              <Route path="/viewer" element={<EvolutionVisualization />}/>
              <Route path="/largeviewer" element={<LargeVisualization />}/>
            </Routes >
        </Router>   
      </div>
    </ChakraProvider>
  );
}

export default App;
