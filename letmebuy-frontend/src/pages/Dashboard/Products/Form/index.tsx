import './styles.css';
import {useForm} from "react-hook-form";
import {Product} from "../../../../types/product";
import {requestBackend } from "../../../../util/requests";
import {AxiosRequestConfig} from "axios";
import {useHistory} from "react-router-dom";

const Form = () => {

    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm<Product>();

    const history = useHistory();

    //Function request basic from backend (POST) products
    const onSubmit = (formData: Product) => {

        const data = { ...formData,
            imgUrl: 'https://i.ibb.co/KXRj7dH/931303c53b5765fbc12a4a5c1e9004a0.png',
            categories: [ {id: 1, name: ""} ],
        }

        const config: AxiosRequestConfig = {
            method: 'POST',
            url: "/products",
            data: data,
            withCredentials: true,
        };

        requestBackend(config).then(() => {
            history.push("/dashboard/products")
        });
    };

    const handleCancel = () => {
        history.push("/dashboard/products")
    }

    return (
        <div className="product-crud-container">
            <div className="base-card product-crud-form-card">
                <h1 className="product-crud-form-title">Dados do Produto</h1>
                <form onSubmit={handleSubmit(onSubmit)}>
                    <div className="row product-crud-inputs-container">
                        <div className="col-lg-6 product-crud-inputs-left-container">
                            <div className="form-floating mb-3 margin-bottom-30">
                                <input
                                    {...register('name', {
                                        required: 'Campo obrigatório',
                                    })}
                                    type="text"
                                    className={`form-control base-input ${errors.name ? 'is-invalid' : ''}`}
                                    id="floatingInput"
                                    placeholder="Nome do Produto"
                                    name="name"
                                />
                                <label htmlFor="floatingInput">Produto</label>
                                <div className="invalid-feedback d-block">{errors.name?.message}</div>
                            </div>
                            <div className="form-floating mb-3 margin-bottom-30">
                                <input type="" className="form-control base-input" id="floatingInput" placeholder="Categoria do Produto" />
                                <label form="floatingInput">Categoria</label>
                            </div>
                            <div className="form-floating mb-3 margin-bottom-30">
                                <input
                                    {...register('price', {
                                        required: 'Campo obrigatório',
                                    })}
                                    type="text"
                                    className={`form-control base-input ${errors.price ? 'is-invalid' : ''}`}
                                    id="floatingInput"
                                    placeholder="Preço do Produto"
                                    name="price"
                                />
                                <label htmlFor="floatingInput">Preço</label>
                                <div className="invalid-feedback d-block">{errors.price?.message}</div>
                            </div>
                        </div>
                        <div className="col-lg-6">
                            <div className="form-floating">
                                <textarea
                                    rows={10}
                                    {...register('description', {
                                        required: 'Campo obrigatório',
                                    })}
                                    className={`form-control base-input h-auto ${errors.description? 'is-invalid' : ''}`}
                                    id="floatingInput"
                                    placeholder="Descrição"
                                    name="description"
                                />
                                <label form="floatingTextarea">Descrição</label>
                                <div className="invalid-feedback d-block">{errors.description?.message}</div>
                            </div>
                        </div>
                    </div>
                    <div className="product-crud-buttons-container">
                        <button
                            className="btn-outline-danger product-crud-button"
                            onClick={handleCancel}
                        >
                            CANCELAR
                        </button>
                        <button
                            className="btn-primary product-crud-button text-white">
                            SALVAR
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default Form;