const debitoId = localStorage.getItem("debitoSelecionadoId");
const clienteId = localStorage.getItem("clienteLogadoId");
const debitoUrl = `http://localhost:8083/debito/${debitoId}`;
const carteiraUrl = "http://localhost:8083/carteira";

document.addEventListener("DOMContentLoaded", () => {
  carregarDebito();
});

let debitoGlobal; // Variável global

function carregarDebito() {
  fetch(debitoUrl)
    .then(res => res.json())
    .then(debito => {
      debitoGlobal = debito; // Guarda globalmente
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
      if (carteira.sdCarteira < debitoGlobal.vlDebito) return alert("Saldo insuficiente.");

      carteira.sdCarteira -= debitoGlobal.vlDebito;

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
      debitoGlobal.dpDebito = new Date().toISOString().split("T")[0];
      debitoGlobal.hpDebito = new Date().toLocaleTimeString();
      debitoGlobal.stDebito = "PAGO";

      return fetch(`http://localhost:8083/debito/${debitoId}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          vlDebito: debitoGlobal.vlDebito,
          dvDebito: debitoGlobal.dvDebito,
          idCliente: debitoGlobal.clDebito.idCliente,
          idAgendamento: debitoGlobal.agDebito.idAgendamento,
          stDebito: debitoGlobal.stDebito
        })
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