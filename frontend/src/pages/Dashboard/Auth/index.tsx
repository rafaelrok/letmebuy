
import { Route, Switch } from 'react-router-dom';
import { ReactComponent as AuthImage } from '../../../assets/images/auth-image.svg';
import Login from './Login';
import Register from './Register';
import Recover from './Recover';

import './styles.css';

const Auth = () => {


    return (
        <div className="auth-container">
            <div className="auth-banner-container">
                <h1>
                    Divulgue seu produtos no letmebuy
                </h1>
                <p>
                    Faça parte do nosso catálogo de divulgação e aumente a venda dos seus produtos.
                </p>
                <AuthImage />
            </div>
            <div className="auth-form-container">
                <Switch>
                    <Route path="/dashboard/auth/login">
                        <Login />
                    </Route>
                    <Route path="/dashboard/auth/register">
                        <Register />
                    </Route>
                    <Route path="/dashboard/auth/recover">
                        <Recover />
                    </Route>
                </Switch>
            </div>
        </div>
    );
}

export default Auth;