import PrivateRoute from "components/PrivateRoute";
import { Switch } from "react-router";
import Navbar from "./Navbar";
import Users from "./User";

import './styles.css';


const Dashboard = () => {

    return (
        <div className="admin-container">
            <Navbar />
            <div className="admin-content">
                <Switch>
                    <PrivateRoute path="/dashboard/products">
                        <h1>Product CRUD</h1>
                    </PrivateRoute>
                    <PrivateRoute path="/dashboard/categories">
                        <h1>Category CRUD</h1>
                    </PrivateRoute>
                    <PrivateRoute path="/dashboard/users">
                        <Users />
                    </PrivateRoute>
                </Switch>
            </div>

        </div>
    );
}

export default Dashboard;