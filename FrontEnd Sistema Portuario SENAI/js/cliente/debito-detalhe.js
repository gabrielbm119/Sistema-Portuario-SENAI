const debitoId = localStorage.getItem("debitoSelecionadoId");
const clienteId = localStorage.getItem("clienteLogadoId");
const debitoUrl = `http://localhost:8083/debito/${debitoId}`;
const carteiraUrl = "http://localhost:8083/carteira";

document.addEventListener("DOMContentLoaded", () => {
  carregarDebito();
});

function carregarDebito() {
  fetch(debitoUrl)
    .then(res => res.json())
    .then(debito => {
      mostrarDebito(debito);
      if (debito.stDebito === "PENDENTE") {
        document.getElementById("pagarBtn").style.display = "inline-block";
      }
    })
    .catch(err => alert("Erro ao buscar débito: " + err.message));
}

function mostrarDebito(debito) {
  const container = document.getElementById("dadosDebito");
  container.innerHTML = `
    <p><strong>ID:</strong> ${debito.idDebito}</p>
    <p><strong>Valor:</strong> R$ ${debito.vlDebito}</p>
    <p><strong>Status:</strong> ${debito.stDebito}</p>
    <p><strong>Data Geração:</strong> ${debito.dgDebito} ${debito.hgDebito}</p>
    <p><strong>Data Validade:</strong> ${debito.dvDebito}</p>
    <p><strong>Data Pagamento:</strong> ${debito.dpDebito || "Não pago"}</p>
  `;
}

function pagarDebito() {
  fetch("http://localhost:8083/carteira")
    .then(res => res.json())
    .then(carteiras => {
      const carteira = carteiras.find(c => c.clCarteira.idCliente == clienteId);
      if (!carteira) return alert("Carteira não encontrada.");
      if (carteira.sdCarteira < debito.vlDebito) return alert("Saldo insuficiente.");

      carteira.sdCarteira -= debito.vlDebito;

      return fetch(`http://localhost:8083/carteira/${carteira.idCarteira}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          sdCarteira: carteira.sdCarteira,
          idCliente: clienteId
        })
      });
    })
    .then(() => {
      return fetch(`http://localhost:8083/debito/${debitoId}`)
        .then(res => res.json())
        .then(debito => {
          debito.dpDebito = new Date().toISOString().split("T")[0];
          debito.hpDebito = new Date().toLocaleTimeString();
          debito.stDebito = "PAGO";

          return fetch(`http://localhost:8083/debito/${debitoId}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
              vlDebito: debito.vlDebito,
              dvDebito: debito.dvDebito,
              idCliente: debito.clDebito.idCliente,
              idAgendamento: debito.agDebito.idAgendamento,
              stDebito: debito.stDebito
            })
          });
        });
    })
    .then(() => {
      alert("Pagamento efetuado com sucesso!");
      window.location.href = "financeiro.html";
    })
    .catch(err => alert("Erro ao pagar débito: " + err.message));
}

function voltar() {
  window.location.href = "financeiro.html";
}