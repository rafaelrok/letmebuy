import { AxiosRequestConfig } from 'axios';
import { useState } from 'react';
import { useEffect } from 'react';
import CurrencyInput from 'react-currency-input-field';
import { useForm, Controller } from 'react-hook-form';
import { useHistory, useParams } from 'react-router-dom';
import Select from 'react-select';
import { Category } from 'types/category';
import { Product } from 'types/product';
import { requestBackend } from 'util/requests';
import { toast } from 'react-toastify';

import Swal from 'sweetalert2';

import './styles.css';

type UrlParams = {
    productId: string;
};

const Form = () => {
    const { productId } = useParams<UrlParams>();

    const isEditing = productId !== 'create';

    const history = useHistory();

    const [selectCategories, setSelectCategories] = useState<Category[]>([]);

    const {
        register,
        handleSubmit,
        formState: { errors },
        setValue,
        control,
    } = useForm<Product>();

    useEffect(() => {
        requestBackend({ url: '/categories' }).then((response) => {
            setSelectCategories(response.data.content);
        });
    }, []);

    useEffect(() => {
        if (isEditing) {
            requestBackend({ url: `/products/${productId}` }).then((response) => {
                const product = response.data as Product;

                setValue('name', product.name);
                setValue('price', product.price);
                setValue('description', product.description);
                setValue('imgUrl', product.imgUrl);
                setValue('categories', product.categories);
            });
        }
    }, [isEditing, productId, setValue]);

    const onSubmit = (formData: Product) => {
        const data = {
            ...formData,
            price: String(formData.price).replace(',', '.'),
        };

        const config: AxiosRequestConfig = {
            method: isEditing ? 'PUT' : 'POST',
            url: isEditing ? `/products/${productId}` : '/products',
            data,
            withCredentials: true,
        };

        requestBackend(config)
            .then(() => {
                if(isEditing){
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Produto atualizado com sucesso',
                        showConfirmButton: false,
                        timer: 1500
                    })
                    history.push('/dashboard/products');
                } else {
                    Swal.fire({
                        position: 'center',
                        icon: 'success',
                        title: 'Produto cadastrado com sucesso',
                        showConfirmButton: false,
                        timer: 1500
                    })
                    history.push('/dashboard/products');
                }
            })
            .catch(() => {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Algo deu errado!',
                    confirmButtonColor: '#DF5753',
                    footer: '<p>Verifiquei os dados</p>'
                })
            });
    };

    const handleCancel = () => {
        history.push('/dashboard/products');
    };

    return (
        <div className="product-crud-container">
            <div className="base-card product-crud-form-card">
                <h1 className="product-crud-form-title">DADOS DO PRODUTO</h1>

                <form onSubmit={handleSubmit(onSubmit)}>
                    <div className="row product-crud-inputs-container">
                        <div className="col-lg-6 product-crud-inputs-left-container">
                            <div className="form-floating mb-3 margin-bottom-30">
                                <input
                                    {...register('name', {
                                        required: 'Campo obrigatório',
                                    })}
                                    type="text"
                                    className={`form-control base-input ${
                                        errors.name ? 'is-invalid' : ''
                                    }`}
                                    id="floatingInput"
                                    placeholder="Nome do produto"
                                    name="name"
                                />
                                <label htmlFor="floatingInput">Produto</label>
                                <div className="invalid-feedback d-block">
                                    {errors.name?.message}
                                </div>
                            </div>

                            <div className="margin-bottom-30 ">
                                <Controller
                                    name="categories"
                                    rules={{ required: true }}
                                    control={control}
                                    render={({ field }) => (
                                        <Select
                                            {...field}
                                            options={selectCategories}
                                            classNamePrefix="product-crud-select"
                                            isMulti
                                            getOptionLabel={(category: Category) => category.name}
                                            getOptionValue={(category: Category) =>
                                                String(category.id)
                                            }
                                        />
                                    )}
                                />
                                {errors.categories && (
                                    <div className="invalid-feedback d-block">
                                        Campo obrigatório
                                    </div>
                                )}
                            </div>

                            <div className="form-floating mb-3 margin-bottom-30">
                                <Controller
                                    name="price"
                                    rules={{ required: 'Campo obrigatório' }}
                                    control={control}
                                    render={({ field }) => (
                                        <CurrencyInput
                                            placeholder="Preço"
                                            className={`form-control base-input ${
                                                errors.name ? 'is-invalid' : ''
                                            }`}
                                            disableGroupSeparators={true}
                                            value={field.value}
                                            onValueChange={field.onChange}
                                            id="floatingInput"
                                        />
                                    )}
                                />
                                <label htmlFor="floatingInput">Preço</label>
                                <div className="invalid-feedback d-block">
                                    {errors.price?.message}
                                </div>
                            </div>

                            <div className="form-floating mb-3 margin-bottom-30">
                                <input
                                    {...register('imgUrl', {
                                        required: 'Campo obrigatório',
                                        pattern: {
                                            value: /^(https?|chrome):\/\/[^\s$.?#].[^\s]*$/gm,
                                            message: 'Deve ser uma URL válida',
                                        },
                                    })}
                                    type="text"
                                    className={`form-control base-input ${
                                        errors.name ? 'is-invalid' : ''
                                    }`}
                                    id="floatingInput"
                                    placeholder="URL da imagem do produto"
                                    name="imgUrl"
                                />
                                <label htmlFor="floatingInput">URL da imagem</label>
                                <div className="invalid-feedback d-block">
                                    {errors.imgUrl?.message}
                                </div>
                            </div>
                        </div>
                        <div className="col-lg-6">
                            <div className="form-floating mb-3">
                                <textarea
                                    rows={10}
                                    {...register('description', {
                                        required: 'Campo obrigatório',
                                    })}
                                    className={`form-control base-input h-auto ${
                                        errors.name ? 'is-invalid' : ''
                                    }`}
                                    id="floatingInput"
                                    placeholder="Descrição"
                                    name="description"
                                />
                                <label htmlFor="floatingInput">Descrição</label>
                                <div className="invalid-feedback d-block">
                                    {errors.description?.message}
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="product-crud-buttons-container">
                        <button
                            className="btn btn-outline-danger product-crud-button-first"
                            onClick={handleCancel}>
                            CANCELAR
                        </button>
                        <button className="btn btn-primary product-crud-button text-white">
                            SALVAR
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default Form;
