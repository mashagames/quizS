var workersTable = document.getElementById('workers'),
    info = document.getElementById('info'),
    requestURL = 'data.json', //файл находится в той же папке, что и html-файл, который к нему обращается
    request = new XMLHttpRequest();
request.open('GET', requestURL);
request.onload = function(e) {
    if (request.readyState === 4) {
        if (request.status === 200) {
            console.log(request.response);
            var dataTable = JSON.parse(request.responseText);
            getTableInfo(dataTable);
        } else {
            console.error(request.statusText);
        }
    }
};
request.onerror = function(e) {
    console.error(request.statusText);
};
request.send();

function getTableInfo(data) {
    info.innerHTML = `<h2>Компания: ${data.companyName}</h2>
         <p>Адрес: ${data.city}, ${data.address}</p> 
         <p>Дата основания: ${data.birthday}</p>`;
    let headerTr = document.createElement('tr');
    headerTr.innerHTML = `<th>ФИО</th><th>Должность</th><th>Email</th>
                         <th>Телефон</th><th>Приемные дни/часы</th>`;
    workersTable.appendChild(headerTr);
 // console.log(Array.isArray(data.workers));
    data.workers.forEach(function(elem) {
        let tr = document.createElement('tr');
        tr.innerHTML = `<td>${elem.name}</td><td>${elem.position}</td>
              <td>${elem.email}</td><td>${elem.phone}</td>
              <td>${elem.active}</td>`;
        workers.appendChild(tr);
    });
}