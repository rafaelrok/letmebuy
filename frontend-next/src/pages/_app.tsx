/* eslint-disable @next/next/no-sync-scripts */

import type { AppProps } from 'next/app'
import Head from 'next/head'

import { Navbar } from '../components'

import "../styles/custom.scss"
import "../styles/globals.css"

function MyApp({ Component, pageProps }: AppProps) {
  return (
    <>
      <Head>
        <meta
          name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"
        />
        <script
          src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
          integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
          crossOrigin="anonymous"
        />
        <title>LetmeBuy</title>
      </Head>
      <Navbar />
      <Component {...pageProps} />
    </>
  )

}


export default MyApp
