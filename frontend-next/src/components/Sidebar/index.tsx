import Link from "next/link";
import { returnActiveItem } from "../../utils";
import { isAllowedByRole } from "../../utils/auth";
import styles from "./sidebar.module.css";

export default function Sidebar() {
  return (
    <div className={styles.adminNavContainer}>
      <ul>
        <li>
          <Link href="/admin/dashboard/products">
            <a
              className={`${
                returnActiveItem("/admin/dashboard/products") +
                " " +
                styles.adminNavItem
              }`}
            >
              Produtos
            </a>
          </Link>
        </li>
        <li>
          <Link href="/admin/dashboard/categories">
            <a
              className={`${
                returnActiveItem("/admin/dashboard/categories") +
                " " +
                styles.adminNavItem
              }`}
            >
              Categorias
            </a>
          </Link>
        </li>
        {isAllowedByRole(["ROLE_ADMIN"]) && (
          <li>
            <Link href="/admin/dashboard/users">
              <a
                className={`${
                  returnActiveItem("/admin/dashboard/users") +
                  " " +
                  styles.adminNavItem
                }`}
              >
                Usuários
              </a>
            </Link>
          </li>
        )}
      </ul>
    </div>
  );
}
