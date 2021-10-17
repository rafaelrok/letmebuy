import { NavLink } from 'react-router-dom';
import { FaBox, FaHive, FaUserAlt } from "react-icons/fa";

import './styles.css';

const Navbar = () => {
    return (
        <nav className="base-card admin-nav-container">
            <ul className="base-card admin-nav-items-container">
                <li>
                    <NavLink to="/dashboard/products" className="admin-nav-item">
                        <FaBox />
                        <p>Produtos</p>
                    </NavLink>
                </li>
                <li>
                    <NavLink to="/dashboard/categories" className="admin-nav-item">
                        <FaHive />
                        <p>Categorias</p>
                    </NavLink>
                </li>
                <li>
                    <NavLink to="/dashboard/users" className="admin-nav-item">
                        <FaUserAlt />
                        <p>Usuários</p>
                    </NavLink>
                </li>
            </ul>
        </nav>
    );
}

export default Navbar;