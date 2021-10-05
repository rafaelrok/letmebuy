import { Route, Switch } from "react-router";
import Navbar from "./Navbar";
import './styles.css';
import Users from "./User";


const Dashboard = () => {

    return (
        <div className="admin-container">
            <Navbar />
            <div className="admin-content">
                <Switch>
                    <Route path="/dashboard/products">
                        <h1>Product CRUD</h1>
                    </Route>
                    <Route path="/dashboard/categories">
                        <h1>Category CRUD</h1>
                    </Route>
                    <Route path="/dashboard/users">
                        <Users />
                    </Route>
                </Switch>
            </div>

        </div>
    );
}

export default Dashboard;