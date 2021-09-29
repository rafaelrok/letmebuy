import './styles.css';
import ProductPrice from 'components/ProductPrice';
import { ReactComponent as ArrowIcon } from '../../assets/images/arrow.svg';

const ProductDetails = () => {
    return (
        <div className="product-detail-container">
            <div className="base-card product-detail-card">
                <div className="goback-container">
                    <ArrowIcon />
                    <h2>VOLTAR</h2>
                </div>
                <div className="row">
                    <div className="col-xl-6">
                        <div className="img-container">
                            <img src="https://i.ibb.co/QKjGx1y/itx-gamer-pc-monstro-i9-99000k-2080-super-work-1600x1600fill-ffffff-640x640-fill-ffffff.png" alt="Nome do Produto"></img>
                        </div>
                        <div className="name-price-container">
                            <h1>Nome do Produto</h1>
                            <ProductPrice price={2354.6}/>
                        </div>
                    </div>
                    <div className="col-xl-6">
                        <div className="description-container">
                            <h2>Descrição do Produto</h2>
                            <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Sint, ducimus.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default ProductDetails;