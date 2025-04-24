const apiUrl = 'http://localhost:8083/doca';

let docas = [];
let docaEdicaoId = null;

document.addEventListener('DOMContentLoaded', carregarDocas);
document.getElementById('criarDocaBtn').addEventListener('click', criarDoca);

function carregarDocas() {
  fetch(apiUrl)
    .then(res => res.json())
    .then(data => {
      docas = data;
      renderizarTabela();
    })
    .catch(err => alert("Erro ao carregar docas: " + err.message));
}

function renderizarTabela() {
  const tabela = document.getElementById('tabelaDocas');
  tabela.innerHTML = "";

  docas.forEach(doca => {
    const tr = document.createElement('tr');
    tr.innerHTML = `
      <td>${doca.idDoca}</td>
      <td>${doca.nmDoca}</td>
      <td>${doca.cmDoca}</td>
      <td>
        <button onclick="editarDoca(${doca.idDoca})">Editar</button>
        <button onclick="deletarDoca(${doca.idDoca})">Deletar</button>
      </td>
    `;
    tabela.appendChild(tr);
  });
}

function criarDoca() {
  const payload = {
    nmDoca: document.getElementById('nmDoca').value,
    cmDoca: parseFloat(document.getElementById('cmDoca').value)
  };

  fetch(apiUrl, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(payload)
  })
    .then(res => {
      if (!res.ok) throw new Error("Erro ao criar doca");
      return res.json();
    })
    .then(() => {
      alert("Doca criada com sucesso!");
      carregarDocas();
    })
    .catch(err => alert(err.message));
}

function editarDoca(id) {
  const doca = docas.find(d => d.idDoca === id);
  if (!doca) return;

  document.getElementById('nmDocaEditar').value = doca.nmDoca;
  document.getElementById('cmDocaEditar').value = doca.cmDoca;

  document.getElementById('formularioEdicao').style.display = 'block';
  docaEdicaoId = id;
}

function salvarEdicao() {
  const payload = {
    nmDoca: document.getElementById('nmDocaEditar').value,
    cmDoca: parseFloat(document.getElementById('cmDocaEditar').value)
  };

  fetch(`${apiUrl}/${docaEdicaoId}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(payload)
  })
    .then(res => {
      if (!res.ok) throw new Error("Erro ao atualizar doca");
      return res.json();
    })
    .then(() => {
      alert("Doca atualizada com sucesso!");
      carregarDocas();
      document.getElementById('formularioEdicao').style.display = 'none';
    })
    .catch(err => alert(err.message));
}

function cancelarEdicao() {
  document.getElementById('formularioEdicao').style.display = 'none';
  docaEdicaoId = null;
}

function deletarDoca(id) {
  if (confirm(`Deseja deletar a doca com ID ${id}?`)) {
    fetch(`${apiUrl}/${id}`, { method: 'DELETE' })
      .then(res => {
        if (!res.ok) throw new Error("Erro ao deletar doca");
        carregarDocas();
      })
      .catch(err => alert(err.message));
  }
}
