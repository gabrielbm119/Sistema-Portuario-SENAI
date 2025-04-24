const apiUrl = "http://localhost:8083/cliente";

document.addEventListener("DOMContentLoaded", carregarClientes);
document.getElementById("criarClienteBtn").addEventListener("click", criarCliente);

function carregarClientes() {
  fetch(apiUrl)
    .then(res => res.json())
    .then(data => renderizarTabela(data))
    .catch(err => alert("Erro ao carregar clientes: " + err.message));
}

function renderizarTabela(clientes) {
  const tabela = document.getElementById("tabelaClientes");
  tabela.innerHTML = "";

  clientes.forEach(cliente => {
    const tr = document.createElement("tr");
    tr.innerHTML = `
      <td>${cliente.idCliente}</td>
      <td>${cliente.nmCliente}</td>
      <td>${cliente.emCliente}</td>
      <td>${cliente.pjCliente}</td>
      <td>${cliente.tfCliente}</td>
      <td>
        <button onclick="visualizarCliente(${cliente.idCliente})">Visualizar</button>
        <button onclick="deletarCliente(${cliente.idCliente})">Deletar</button>
      </td>
    `;
    tabela.appendChild(tr);
  });
}

function criarCliente() {
  const payload = {
    nmCliente: document.getElementById("nmCliente").value,
    emCliente: document.getElementById("emCliente").value,
    snCliente: document.getElementById("snCliente").value,
    pjCliente: document.getElementById("pjCliente").value,
    tfCliente: document.getElementById("tfCliente").value,
  };

  fetch(apiUrl, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(payload),
  })
    .then(res => {
      if (!res.ok) throw new Error("Erro ao criar cliente");
      return res.json();
    })
    .then(() => {
      alert("Cliente criado com sucesso!");
      carregarClientes();
    })
    .catch(err => alert(err.message));
}

function deletarCliente(id) {
  if (confirm(`Deseja deletar o cliente com ID ${id}?`)) {
    fetch(`${apiUrl}/${id}`, { method: "DELETE" })
      .then(res => {
        if (!res.ok) throw new Error("Erro ao deletar cliente");
        carregarClientes();
      })
      .catch(err => alert(err.message));
  }
}

function visualizarCliente(id) {
  localStorage.setItem("clienteSelecionadoId", id);
  window.location.href = "cliente-detalhes.html";
}