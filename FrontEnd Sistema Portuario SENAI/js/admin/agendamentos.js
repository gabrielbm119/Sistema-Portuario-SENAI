const apiUrl = 'http://localhost:8083/agendamento';

document.addEventListener('DOMContentLoaded', carregarTodosAgendamentos);
document.getElementById('filtrarBtn').addEventListener('click', filtrarPorData);
document.getElementById('criarAgendamentoBtn').addEventListener('click', criarAgendamento);

function carregarTodosAgendamentos() {
    fetch(apiUrl)
        .then(res => res.json())
        .then(data => renderizarTabela(data))
        .catch(err => alert("Erro ao carregar agendamentos: " + err.message));
}

function filtrarPorData() {
    const dataSelecionada = document.getElementById('filtroData').value;
    if (!dataSelecionada) return carregarTodosAgendamentos(); // Se não houver data selecionada, carrega todos os agendamentos

    // Certifique-se de que a data no backend está sendo comparada corretamente
    const dataFormatada = new Date(dataSelecionada).toISOString().split('T')[0]; // Formato yyyy-mm-dd
    
    fetch(`${apiUrl}?data=${dataFormatada}`)
        .then(res => res.json())
        .then(data => renderizarTabela(data))
        .catch(err => alert("Erro ao filtrar: " + err.message));
}

function criarAgendamento() {
    const payload = {
        idNavio: parseInt(document.getElementById('idNavio').value),
        idDoca: parseInt(document.getElementById('idDoca').value),
        diAgendamento: document.getElementById('diAgendamento').value,
        hiAgendamento: document.getElementById('hiAgendamento').value,
        dfAgendamento: document.getElementById('dfAgendamento').value,
        hfAgendamento: document.getElementById('hfAgendamento').value
    };

    fetch(apiUrl, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
    })
    .then(res => {
        if (!res.ok) throw new Error("Erro ao criar agendamento");
        return res.json();
    })
    .then(() => {
        alert("Agendamento criado com sucesso!");
        carregarTodosAgendamentos();
    })
    .catch(err => alert(err.message));
}

function atualizarAgendamento(id, novoPayload) {
    fetch(`${apiUrl}/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(novoPayload)
    })
    .then(res => {
        if (!res.ok) throw new Error("Erro ao atualizar");
        return res.json();
    })
    .then(() => carregarTodosAgendamentos())
    .catch(err => alert(err.message));
}

function deletarAgendamento(id) {
    if (confirm(`Deseja deletar o agendamento com ID ${id}?`)) {
        fetch(`${apiUrl}/${id}`, { method: 'DELETE' })
            .then(res => {
                if (!res.ok) throw new Error("Erro ao deletar");
                carregarTodosAgendamentos();
            })
            .catch(err => alert(err.message));
    }
}

function renderizarTabela(agendamentos) {
    const tabela = document.getElementById('tabelaAgendamentos');
    tabela.innerHTML = "";

    agendamentos.forEach(ag => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${ag.idAgendamento}</td>
            <td>${ag.nvAgendamento?.idNavio || 'N/A'}</td>
            <td>${ag.dcAgendamento?.idDoca || 'N/A'}</td>
            <td>
                Início: ${ag.diAgendamento} ${ag.hiAgendamento}<br>
                Fim: ${ag.dfAgendamento} ${ag.hfAgendamento}
            </td>
            <td>${ag.stAgentamento}</td>
            <td>
                <button onclick="editarAgendamento(${ag.idAgendamento})">Editar</button>
                <button onclick="deletarAgendamento(${ag.idAgendamento})">Deletar</button>
            </td>
        `;
        tabela.appendChild(tr);
    });
}

function editarAgendamento(id) {
    const agendamento = agendamentos.find(ag => ag.idAgendamento === id);

    document.getElementById('idNavioEditar').value = agendamento.nvAgendamento.idNavio;
    document.getElementById('idDocaEditar').value = agendamento.dcAgendamento.idDoca;
    document.getElementById('diAgendamentoEditar').value = agendamento.diAgendamento;
    document.getElementById('hiAgendamentoEditar').value = agendamento.hiAgendamento;
    document.getElementById('dfAgendamentoEditar').value = agendamento.dfAgendamento;
    document.getElementById('hfAgendamentoEditar').value = agendamento.hfAgendamento;
    document.getElementById('stAgendamentoEditar').value = agendamento.stAgendamento;

    document.getElementById('formularioEdicao').style.display = 'block';
    agendamentoEdicaoId = agendamento.idAgendamento;
}

function salvarEdicao() {
    const payload = {
        idNavio: parseInt(document.getElementById('idNavioEditar').value),
        idDoca: parseInt(document.getElementById('idDocaEditar').value),
        diAgendamento: document.getElementById('diAgendamentoEditar').value,
        hiAgendamento: document.getElementById('hiAgendamentoEditar').value,
        dfAgendamento: document.getElementById('dfAgendamentoEditar').value,
        hfAgendamento: document.getElementById('hfAgendamentoEditar').value,
        stAgendamento: document.getElementById('stAgendamentoEditar').value
    };

    fetch(`${apiUrl}/${agendamentoEdicaoId}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
    })
    .then(res => {
        if (!res.ok) throw new Error("Erro ao atualizar agendamento");
        return res.json();
    })
    .then(() => {
        alert("Agendamento atualizado com sucesso!");
        carregarTodosAgendamentos();
        document.getElementById('formularioEdicao').style.display = 'none';
    })
    .catch(err => alert(err.message));
}

function cancelarEdicao() {
    document.getElementById('formularioEdicao').style.display = 'none';  // Ocultar o formulário
}

let agendamentos = [];  // Declare a variável global

function carregarTodosAgendamentos() {
    fetch(apiUrl)
        .then(res => res.json())
        .then(data => {
            agendamentos = data;  // Atualiza a lista global de agendamentos
            renderizarTabela(agendamentos);  // Passa a lista de agendamentos para a função de renderização
        })
        .catch(err => alert("Erro ao carregar agendamentos: " + err.message));
}
