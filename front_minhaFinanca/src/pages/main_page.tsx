import "./main.css";
import { useEffect, useState } from "react";
import logo from "../assets/logo.svg";
import api from "../ConectionApi";
import despesa from "../assets/despesa.svg";
import receita from "../assets/receita.svg";
import divida from "../assets/divida.svg";
import home from "../assets/home.svg";

export function MainPage(){

    const [abrir, setAbrir] = useState(false);
    const [botaoAbrir, setbotaoAbrir] = useState(false);
    const [topH, setTopH] = useState(true);
    const [midH, setMidH] = useState(true);
    const [botH, setBotH] = useState(true);
    const [conteudo, setConteudo] = useState(false);
    const [ valorSaldoVermelho, setvalorSaldoVermelho ] = useState(false);
    const [ transation, setTransation ] = useState<any[]>([]);

    type Summary = {
        receita: number;
        despesa: number;
        divida: number;
        saldo: number;
    };

    const [sumary, setSumary] = useState<Summary | null>(null);

    useEffect(() => {
    async function load() {
        const data = await api.getSumary();
        setSumary(data);
        verificarValorSaldo(data?.saldo ?? 0);
        const transationData = await api.getTransation();
        setTransation(transationData ?? []);
    }
    load();
    }, []);

    function verificarValorSaldo(valor: number) {
        if (valor > 0) {
            return setvalorSaldoVermelho(false);
        } else {
            return setvalorSaldoVermelho(true);
        }
    }

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
                <ul className="menu">
                    <li>
                        <div>
                            <h3><img src={home} alt="" /></h3>
                            <span><h3>Home</h3></span>
                        </div>
                    </li>
                    <li>
                        <div>
                            <h3><img src={receita} alt="" /></h3>
                            <span><h3>Receita</h3></span>
                        </div>
                    </li>
                    <li>
                        <div>
                            <h3><img src={despesa} alt="" /></h3>
                            <span><h3>Despesas</h3></span>
                        </div>
                    </li>
                    <li>
                        <div>
                            <h3><img src={divida} alt="" /></h3>
                            <span><h3>Divida</h3></span>
                        </div>
                    </li>
                </ul>
            </div>
        </section>


        <section className={conteudo ? "conteudoFechado" : "conteudoAberto"}>
            <img className="imgLogo" src={logo} alt="" />
            <div className="valores">
                <div className="infos receita">
                    <h2>Receita</h2>
                    <p>R$ {Number(sumary?.receita ?? 0).toFixed(2)}</p>
                </div>
                <div className="infos despesas">
                    <h2>Despesas</h2>
                    <p>R$ {Number(sumary?.despesa ?? 0).toFixed(2)}</p>
                </div>
                <div className="infos divida">
                    <h2>Divida</h2>
                    <p>R$ {Number(sumary?.divida ?? 0).toFixed(2)}</p>
                </div>
                <div className="infos saldo">
                    <h2>Saldo</h2>
                    <p className={valorSaldoVermelho ? "vermelho" : "verde"}>R$ {Number(sumary?.saldo ?? 0).toFixed(2)}</p>
                </div>
            </div>

            <div className="historico">
                <table>
                    <thead>
                        <tr>
                            <th>Valor</th>
                            <th>Descrição</th>
                            <th>Data</th>
                            <th>Tipo</th>
                        </tr>
                    </thead>
                    <tbody>
                        {transation.map((transation) => (
                            <tr key={transation.id}>
                                <td>{Number(transation.valor).toFixed(2)}</td>
                                <td>{transation.descricao}</td>
                                <td>{transation.data}</td>
                                <td className={
                                    transation.type === "Receita"
                                        ? "verde"
                                        : transation.type === "Despesa"
                                        ? "amarelo"
                                        : transation.type === "Divida"
                                        ? "vermelho"
                                        : ""
                                    }>{transation.type}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>

        </section>
        </>
    )
}