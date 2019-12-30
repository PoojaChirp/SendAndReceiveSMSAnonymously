
function mapFunction(e){
    e.preventDefault();

    //retrieve the values from the html page
    var from = document.getElementById('phone1').value;
    var to = document.getElementById('phone2').value;

    var data ={"phone1":from, "phone2": to};

    // construct an HTTP request
    var xhr = new XMLHttpRequest();
    xhr.open("post", "/mappings", true);
    xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');

    // send the collected data as JSON
    xhr.send(JSON.stringify(data));
    document.getElementById('refresh').click();

};

function displayMaps(e){
    e.preventDefault()
    var request = new XMLHttpRequest();

//when the state changes to 200, that means the GET call was successful and data is available in this.responseText field
    request.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);

            //Append  to the html table
            var data = JSON.parse(this.responseText);
            var table = document.getElementById("mappings_list" );
            table.innerHTML =  "<tr> <th>Phone1</th> <th>Phone2</th><th>Delete</th> </tr>"
            data.forEach( copyRows);

        }
    };

    function copyRows(value){
        var str ="";
        var table = document.getElementById("mappings_list" );
        var newRow =table.insertRow();
        str = `${str}<td>${value.phone1}</td><td>${value.phone2}</td><td><button onclick="deleteMapping(${value.id})">Delete</button></td>`
        newRow.innerHTML=str;
    };




    request.open('GET', '/mappings', true);
    request.send();

};

function deleteMapping(id){
    var xhr = new XMLHttpRequest();
    xhr.open("delete", `/mappings/${id}`, true);
    xhr.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
    xhr.send();

};

document.getElementById('refresh').click();