
import Image from 'next/image';
import Link from 'next/link';
import { ButtonIcon } from '../components';
import styles from '../styles/Pages/home.module.css';
import HomePage from '../assets/images/main-image.svg';

const Home = () => {
  return (
    <div className={styles.homeContainer}>
      <div className={`row d-flex flex-column-reverse align-items-center justify-content-center flex-lg-row
      card-base border-radius-20 ${styles.homeContent}`} >
        <div className={`col-lg-6 d-flex flex-column align-items-center mt-md-0 ${styles.homeText}`}>
          <h1 className={`${styles.textTitle}`}>Venha conhecer nossos produtos de qualidade</h1>
          <p className={`${styles.textSubtitle}`}>Ajudaremos você a encontrar os melhores produtos disponíveis no mercado.</p>
          <Link href="/catalog">
            <a>
              <ButtonIcon label='inicie agora a sua busca' type={'button'} />
            </a>
          </Link>
        </div>
        <div className={`col-lg d-flex align-items-center justify-content-center align-items-lg-center justify-content-lgstart ${styles.homeImgContainer}`}>
          <Image src={HomePage} alt="Home page Image" />
        </div>
      </div>
    </div>

  )
}

export default Home
