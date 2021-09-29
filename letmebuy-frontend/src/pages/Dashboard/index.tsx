import Navbar from "./Navbar";
import './styles.css';


const Dashboard = () => {

    return (
        <div className="admin-container">
            <Navbar />
            <div className="admin-content">
                <h1>Conteudo</h1>
            </div>
            
        </div>
    );
}

export default Dashboard;