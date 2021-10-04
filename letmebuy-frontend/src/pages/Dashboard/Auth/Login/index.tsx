import { Link } from 'react-router-dom';
import ButtonIcon from 'components/ButtonIcon';

import './styles.css';
import { useForm } from 'react-hook-form';
import { requestBackendLogin } from 'util/requests';
import { useState } from 'react';

type FormData = {
    username: string;
    password: string;
}

const Login = () => {

    const [hasError, setHasError] = useState(false);

    const { register, handleSubmit, formState: { errors } } = useForm<FormData>();

    const onSubmit = (formData: FormData) => {
        requestBackendLogin(formData)
            .then(response => {
                setHasError(false);
                console.log('SUCESSO', response);
            })
            .catch(err => {
                setHasError(true);
                console.log('ERROR', err);
            });
    };


    return (
        <div className="base-card login-card">
            <h1>LOGIN</h1>
            {hasError && (
                <div className="alert alert-danger">
                    Erro ao efetuar login!
                </div>
            )}
            <form onSubmit={handleSubmit(onSubmit)}>
                <div className="mb-4">
                    <input
                        {...register('username', {
                            required: 'Campo obrigatório',
                            pattern: {
                                value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i,
                                message: 'Email inválido'
                            }
                        })}
                        type="text"
                        className="form-control base-input"
                        placeholder="Email"
                        name="username"
                    />
                    <div className="invalid-feedback d-block">{errors.username?.message}</div>
                </div>
                <div className="mb-2">
                    <input
                        {...register('password', {
                            required: 'Campo obrigatório'
                        })}
                        type="password"
                        className="form-control base-input "
                        placeholder="Password"
                        name="password"
                    />
                    <div className="invalid-feedback d-block">{errors.password?.message}</div>
                </div>
                <Link to="/admin/auth/recover" className="login-link-recover">
                    Esqueci a senha
                </Link>
                <div className="login-submit">
                    <ButtonIcon text="Entrar" />
                </div>
                <div className="signup-container">
                    <span className="not-registered">Não tem Cadastro?</span>
                    <Link to="/admin/auth/register" className="login-link-register">
                        Registre-se
                    </Link>
                </div>
            </form>
        </div>
    );
};

export default Login;