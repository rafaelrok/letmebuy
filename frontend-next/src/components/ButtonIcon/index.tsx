import { ButtonIconProps } from "../../@types";
import styles from "./buttonicon.module.css";

import Image from "next/image";
import arrow from "../../assets/images/arrow.svg";


export default function ButtonIcon({ label }: ButtonIconProps) {
    return (
        <div className={`d-flex ${styles.buttonContainer}`}>
            <button className={`btn- btn-primary ${styles.btnIcon}`}>
                <h5>{label}</h5>
            </button>
            <div className={styles.btnIconContainer}>
                <Image src={arrow} alt="Arrow" />
            </div>
        </div>
    );
}

