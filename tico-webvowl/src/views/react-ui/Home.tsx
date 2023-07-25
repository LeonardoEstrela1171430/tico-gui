import { Link } from "react-router-dom";

function Home() {
  
  return (
    <div className="Home">
        Hello this is home

        <Link 
          to="/workspace?id=1"
        >
          teste
          </Link>

        
    </div>
  );
}

export default Home;
