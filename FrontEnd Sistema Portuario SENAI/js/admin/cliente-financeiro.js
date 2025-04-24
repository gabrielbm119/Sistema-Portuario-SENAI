const clienteId = localStorage.getItem("clienteSelecionadoId");

const apiCarteira = "http://localhost:8083/carteira";
const apiDebito = "http://localhost:8083/debito";

document.addEventListener("DOMContentLoaded", () => {
  carregarCarteira();
  carregarDebitos();
});

function carregarCarteira() {
  fetch(apiCarteira)
    .then(res => res.json())
    .then(data => {
      const carteira = data.find(c => c.clCarteira?.idCliente == clienteId);
      document.getElementById("saldoCarteira").textContent = carteira?.sdCarteira || "0.00";
    })
    .catch(err => alert("Erro ao carregar carteira: " + err.message));
}

function carregarDebitos() {
  fetch(apiDebito)
    .then(res => res.json())
    .then(data => {
      const debitosCliente = data.filter(d => d.clDebito?.idCliente == clienteId);
      renderizarDebitos(debitosCliente);
    })
    .catch(err => alert("Erro ao carregar débitos: " + err.message));
}

function renderizarDebitos(debitos) {
  const tabela = document.getElementById("tabelaDebitos");
  tabela.innerHTML = "";

  debitos.forEach(d => {
    const tr = document.createElement("tr");
    tr.innerHTML = `
      <td>${d.idDebito}</td>
      <td>R$ ${d.vlDebito}</td>
      <td>${d.stDebito}</td>
      <td>${d.dgDebito} ${d.hgDebito}</td>
      <td>${d.dvDebito}</td>
      <td>
        <button onclick="editarDebito(${d.idDebito})">Editar</button>
        <button onclick="deletarDebito(${d.idDebito})">Deletar</button>
      </td>
    `;
    tabela.appendChild(tr);
  });
}

function criarDebito() {
  const payload = {
    idAgendamento: parseInt(document.getElementById("idAgendamento").value),
    vlDebito: parseFloat(document.getElementById("vlDebito").value),
    dvDebito: document.getElementById("dvDebito").value
  };

  fetch(apiDebito, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(payload)
  })
    .then(res => {
      if (!res.ok) throw new Error("Erro ao criar débito");
      return res.json();
    })
    .then(() => {
      alert("Débito criado com sucesso!");
      carregarDebitos();
    })
    .catch(err => alert(err.message));
}

function deletarDebito(id) {
  if (confirm(`Deseja deletar o débito ${id}?`)) {
    fetch(`${apiDebito}/${id}`, { method: "DELETE" })
      .then(res => {
        if (!res.ok) throw new Error("Erro ao deletar débito");
        carregarDebitos();
      })
      .catch(err => alert(err.message));
  }
}

function editarDebito(id) {
  alert("A funcionalidade de edição ainda pode ser implementada com um formulário separado caso deseje.");
}
