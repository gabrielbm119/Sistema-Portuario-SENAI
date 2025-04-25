const clienteId = localStorage.getItem("clienteLogadoId");
const carteiraUrl = "http://localhost:8083/carteira";
const debitoUrl = "http://localhost:8083/debito";

document.addEventListener("DOMContentLoaded", () => {
  carregarCarteira();
  carregarDebitos();
});

function carregarCarteira() {
  fetch(carteiraUrl)
    .then(res => res.json())
    .then(carteiras => {
      const carteiraCliente = carteiras.find(c => c.clCarteira.idCliente == clienteId);
      if (carteiraCliente) {
        localStorage.setItem("carteiraId", carteiraCliente.idCarteira);
        document.getElementById("saldoCarteira").textContent = carteiraCliente.sdCarteira.toFixed(2);
      }
    })
    .catch(err => alert("Erro ao buscar carteira: " + err.message));
}

function carregarDebitos() {
  fetch(debitoUrl)
    .then(res => res.json())
    .then(debitos => {
      const debitosCliente = debitos.filter(d => d.clDebito.idCliente == clienteId);
      renderizarDebitos(debitosCliente);
    })
    .catch(err => alert("Erro ao buscar débitos: " + err.message));
}

function renderizarDebitos(debitos) {
  const tabela = document.getElementById("tabelaDebitosCliente");
  tabela.innerHTML = "";

  debitos.forEach(d => {
    const tr = document.createElement("tr");
    tr.innerHTML = `
      <td>${d.idDebito}</td>
      <td>R$ ${d.vlDebito}</td>
      <td>${d.stDebito}</td>
      <td><button onclick="verDebito(${d.idDebito})">Ver</button></td>
    `;
    tabela.appendChild(tr);
  });
}

function adicionarSaldo() {
  alterarSaldo("adicionar");
}

function resgatarSaldo() {
  alterarSaldo("resgatar");
}

function alterarSaldo(tipo) {
  const valor = parseFloat(document.getElementById("valor").value);
  if (isNaN(valor) || valor <= 0) {
    return alert("Informe um valor válido.");
  }

  const carteiraId = localStorage.getItem("carteiraId");

  fetch(`${carteiraUrl}/${carteiraId}`)
    .then(res => res.json())
    .then(carteira => {
      if (tipo === "adicionar") {
        carteira.sdCarteira += valor;
      } else if (tipo === "resgatar" && carteira.sdCarteira >= valor) {
        carteira.sdCarteira -= valor;
      } else {
        return alert("Saldo insuficiente.");
      }

      return fetch(`${carteiraUrl}/${carteiraId}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          sdCarteira: carteira.sdCarteira,
          idCliente: clienteId
        })
      });
    })
    .then(() => {
      alert("Operação realizada com sucesso.");
      carregarCarteira();
    })
    .catch(err => alert("Erro na operação: " + err.message));
}

function verDebito(idDebito) {
  localStorage.setItem("debitoSelecionadoId", idDebito);
  window.location.href = "debito-detalhe.html";
}
