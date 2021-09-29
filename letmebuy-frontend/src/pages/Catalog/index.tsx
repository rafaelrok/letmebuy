import Pagination from "components/Pagination";
import ProductCard from "components/ProductCard";
import { Link } from "react-router-dom";
import { Product } from "types/product";

import './stykes.css';

const Catalog = () => {

    const product: Product = {
        "id": 2,
        "name": "Smart TV",
        "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        "price": 2190.0,
        "imgUrl": "https://i.ibb.co/QKjGx1y/itx-gamer-pc-monstro-i9-99000k-2080-super-work-1600x1600fill-ffffff-640x640-fill-ffffff.png",
        "date": "2020-07-14T10:00:00Z",
        "categories": [
            {
                "id": 1,
                "name": "Livros"
            },
            {
                "id": 3,
                "name": "Computadores"
            }
        ]
    }

    return (
        <div className="container my-4 catalog-container">
            <div className="row catalog-title-contianer">
                <h1>Catalo de Produtos</h1>
            </div>
            <div className="row">
                <div className="col-sm-6 col-lg-4 col-xl-3">
                    <Link to ="/products/1">
                        <ProductCard product={product} />
                    </Link>
                </div>
                <div className="col-sm-6 col-lg-4 col-xl-3">
                    <Link to="/products/2">
                        <ProductCard product={product} />
                    </Link>
                </div>
                <div className="col-sm-6 col-lg-4 col-xl-3">
                    <Link to="/products/3">
                        <ProductCard product={product} />
                    </Link>
                </div>
                <div className="col-sm-6 col-lg-4 col-xl-3">
                    <Link to="/products/4">
                        <ProductCard product={product} />
                    </Link>
                </div>
                <div className="col-sm-6 col-lg-4 col-xl-3">
                    <Link to="/products/5">
                        <ProductCard product={product} />
                    </Link>
                </div>
            </div>
            <div className="row">
                <Pagination />
            </div>
        </div >
    );
}

export default Catalog;