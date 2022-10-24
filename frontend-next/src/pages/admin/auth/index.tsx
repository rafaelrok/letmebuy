import Image from "next/image";
import Link from "next/link";
import { useForm } from "react-hook-form";
import { useRouter } from "next/router";
import { ButtonIcon } from "../../../components";
import { isAuthenticated, loginUser } from "../../../utils/auth";

import imageBackground from "../../../../public/auth_image.png";
import styles from "../../../styles/pages/auth.module.css";
import { useEffect, useState } from "react";

type AuthProps = {
  username: string;
  password: string;
};

export default function Login() {
  const [logged, setLogged] = useState(false);
  const router = useRouter();
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const onSubmit = async (data: AuthProps) => {
    const { username, password } = data;
    await loginUser(username, password);

    const test = isAuthenticated();
    console.log(test);
  };

  //   useEffect(() => {
  //     if (localStorage.getItem("@dscatalog/token")) {
  //       console.log("ok estou logado");
  //     }
  //   }, [isAuthenticated]);

  return (
    <div className={styles.authContainer}>
      <div className={`d-none d-lg-block ${styles.authInfo}`}>
        <h1 className={styles.authInfoTitle}>
          Divulgue seus produtos <br /> no DS Catalog
        </h1>
        <p className={styles.authInfoSubtitle}>
          Faça parte do nosso catálogo de divulgação e <br /> aumente a venda
          dos seus produtos.
        </p>
        <Image src={imageBackground} alt="Background Login" />
      </div>
      <div className={`card-base ${styles.authCard}`}>
        <div className={styles.authContent}>
          <>
            <form
              className={`d-flex flex-column align-items-center justify-content-between ${styles.loginForm}`}
              onSubmit={handleSubmit(onSubmit)}
            >
              <h2 className={`text-center mb-5 ${styles.formTitle}`}>Login</h2>

              <input
                type="text"
                className="form-control input-base"
                placeholder="Email"
                {...register("username", {
                  required: true,
                  pattern: /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/g,
                })}
              />
              {errors.username?.type === "required" && (
                <p className="login-form-error">
                  O Preenchimento do email é obrigatório
                </p>
              )}
              {errors.username?.type === "pattern" && (
                <p className="login-form-error">Insira um email válido!</p>
              )}

              <input
                type="password"
                className="form-control input-base"
                placeholder="Senha"
                {...register("password", { required: true })}
              />
              {errors.password?.type === "required" && (
                <p className="login-form-error">
                  O Preenchimento da senha é obrigatório
                </p>
              )}
              <Link href="/">
                <a className={styles.loginLinkRecover}>Esqueci a senha?</a>
              </Link>
              <div
                className={`d-flex align-items-center justify-content-center ${styles.loginSubmit}`}
              >
                <ButtonIcon label="fazer login" type="submit" />
              </div>
              <div className="text-center">
                <span className={styles.notRegistered}>Não tem Cadastro?</span>
                <Link href="/auth/register">
                  <a className={styles.loginLinkRegister}>CADASTRAR</a>
                </Link>
              </div>
            </form>
          </>
        </div>
      </div>
    </div>
  );
}
