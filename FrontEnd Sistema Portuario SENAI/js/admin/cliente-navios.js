const clienteId = localStorage.getItem("clienteSelecionadoId");

const apiNavio = "http://localhost:8083/navio";

document.addEventListener("DOMContentLoaded", carregarNavios);

function carregarNavios() {
  fetch(apiNavio)
    .then(res => res.json())
    .then(data => {
      const naviosCliente = data.filter(n => n.clNavio?.idCliente == clienteId);
      renderizarTabela(naviosCliente);
    })
    .catch(err => alert("Erro ao carregar navios: " + err.message));
}

function renderizarTabela(navios) {
  const tabela = document.getElementById("tabelaNavios");
  tabela.innerHTML = "";

  navios.forEach(n => {
    const tr = document.createElement("tr");
    tr.innerHTML = `
      <td>${n.idNavio}</td>
      <td>${n.nmNavio}</td>
      <td>${n.tpNavio}</td>
      <td>${n.cmNavio} m</td>
      <td>
        <button onclick="editarNavio(${n.idNavio})">Editar</button>
        <button onclick="deletarNavio(${n.idNavio})">Deletar</button>
      </td>
    `;
    tabela.appendChild(tr);
  });
}

function criarNavio() {
  const payload = {
    nmNavio: document.getElementById("nmNavio").value,
    tpNavio: document.getElementById("tpNavio").value,
    cmNavio: parseFloat(document.getElementById("cmNavio").value),
    idCliente: parseInt(clienteId)
  };

  fetch(apiNavio, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(payload)
  })
    .then(res => {
      if (!res.ok) throw new Error("Erro ao criar navio");
      return res.json();
    })
    .then(() => {
      alert("Navio criado com sucesso!");
      carregarNavios();
    })
    .catch(err => alert(err.message));
}

function deletarNavio(id) {
  if (confirm(`Deseja deletar o navio ${id}?`)) {
    fetch(`${apiNavio}/${id}`, { method: "DELETE" })
      .then(res => {
        if (!res.ok) throw new Error("Erro ao deletar navio");
        carregarNavios();
      })
      .catch(err => alert(err.message));
  }
}

function editarNavio(id) {
  alert("Funcionalidade de edição pode ser adicionada em tela separada ou modal.");
}
