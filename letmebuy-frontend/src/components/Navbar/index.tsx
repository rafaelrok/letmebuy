import './styles.css';
import 'bootstrap/js/src/collapse.js';
import { Link, NavLink } from 'react-router-dom';
import { getTokenData, isAuthenticated, removeAuthData, TokenData } from 'util/requests';
import { useEffect, useState } from 'react';
import history from 'util/history';


type AuthData = {
    authenticated: boolean;
    tokenData?: TokenData
}

const Navbar = () => {

    const [authData, setAuthData] = useState<AuthData>({ authenticated: false });

    useEffect(() => {
        if (isAuthenticated()) {
            setAuthData({
                authenticated: true,
                tokenData: getTokenData()
            });
        }
        else {
            setAuthData({
                authenticated: false
            });
        }
    }, []);

    const handleLogoutClick = (event: React.MouseEvent<HTMLAnchorElement>) => {
        event.preventDefault();
        removeAuthData();
        setAuthData({
            authenticated: false
        });
        history.replace('/');
    }

    return (
        <>
            <nav className="navbar navbar-light navbar-expand-md navbar-fixed-top navigation-clean-button">
                <div className="container">
                    <button
                        data-bs-toggle="collapse"
                        className="navbar-toggler"
                        data-bs-target="#navcol-1"
                        aria-expanded="false"
                        aria-label="Toggle navigation">
                        <span className="visually-hidden">Toggle navigation</span>
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div><Link className="navbar-brand" to="/"><span>Letmebuy</span> </Link></div>
                    <div className="collapse navbar-collapse" id="navcol-1">
                        <ul className="navbar-nav nav-right">
                            <li ><NavLink to="/" activeClassName="active">home </NavLink></li>
                            <li ><NavLink to="/products" activeClassName="active">produtos</NavLink></li>
                            <li ><NavLink to="/dashboard" activeClassName="active">dashboard</NavLink></li>
                            <li ><NavLink to="/contato" activeClassName="active">contato</NavLink></li>
                        </ul>
                        <p className="ms-auto navbar-text actions">
                            {
                                authData.authenticated ? (
                                    <>
                                        <span>{authData.tokenData?.user_name}</span>
                                        <NavLink
                                            to="/"
                                            onClick={handleLogoutClick}
                                            className="login"
                                            activeClassName="active">
                                            Logout
                                        </NavLink>
                                    </>
                                ) : (
                                    <NavLink
                                        className="login"
                                        to="/dashboard/auth/login"
                                        activeClassName="active">
                                        Log In
                                    </NavLink>
                                )
                            }
                            <NavLink
                                className="btn btn-light action-button"
                                role="button" to="/signup"
                                activeClassName="active">
                                Sign Up
                            </NavLink>
                        </p>
                    </div>

                    <div>
                    </div>

                </div>
            </nav>
        </>
    );
}

export default Navbar;