import "./main.css";
import { useState } from "react";
import logo from "../assets/logo.svg";

export function MainPage(){

    const [abrir, setAbrir] = useState(false);
    const [botaoAbrir, setbotaoAbrir] = useState(false);
    const [topH, setTopH] = useState(true);
    const [midH, setMidH] = useState(true);
    const [botH, setBotH] = useState(true);
    const [conteudo, setConteudo] = useState(false);
    
    function menuMostrar(){
        setAbrir(!abrir);
        setbotaoAbrir(!botaoAbrir);
        setTopH(!topH);
        setMidH(!midH);
        setBotH(!botH);
        setConteudo(!conteudo);
    }

    return(
        <>
        <section className={abrir ? "abrir" : "fechar"}>
            <div className="container">
                <div className={botaoAbrir ? "botaoAbrir" : "botaoFechar"} onClick={menuMostrar}>
                    <div className="botao">
                        <div className={topH ? "topHFechado" : "topHAberto"}></div>
                        <div className={midH ? "midHFechado" : "midHAberto"}></div>
                        <div className={botH ? "botHFechado" : "botHAberto"}></div>
                    </div>
                </div>
                <ul>
                    <li><div>1º <span>Home</span></div></li>
                    <li><div>2º <span>Receita</span></div></li>
                    <li><div>3º <span>Despesas</span></div></li>
                    <li><div>4º <span>Divida</span></div></li>
                </ul>
            </div>
        </section>

        <section className={conteudo ? "conteudoFechado" : "conteudoAberto"}>
            <img className="imgLogo" src={logo} alt="" />
        </section>
        </>
    )
}