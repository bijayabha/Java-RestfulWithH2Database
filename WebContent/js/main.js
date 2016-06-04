// The root URL for the REST service
var rootURL = "http://localhost:8080/RESTExampleJava/rest/employees";

var currentEmployee;
findAll();

// Register listeners
$('#btnSearch').click(function() {
	search($('#searchKey').val());
	return false;
});

// Trigger search when pressing 'Return' on search key input field
$('#searchKey').keypress(function(e){
	if(e.which == 13) {
		search($('#searchKey').val());
		e.preventDefault();
		return false;
    }
});

$('#btnSave').click(function() {
	if ($('#employeeId').val() == ''){
		alert("addEmployee");
		addEmployee();
	}	
	else{
		updateEmployee();
	}		
	return false;
});

$('#btnClear').click(function() {
	$('#employeeForm').trigger("reset");		
	return false;
});
$('#btnDelete').click(function() {
	deleteEmployee();
	return false;
});

$('#employeeList a').live('click', function() {
	findById($(this).data('identity'));
});

function search(searchKey) {
	if (searchKey == '') 
		findAll();
	else
		findByName(searchKey);
}

function findAll() {
	console.log('findAll');
	$.ajax({
		type: 'GET',
		url: rootURL,
//		dataType: 'json', // data type of response
		success: function(data){
			renderList(data);
		}
	});
}

function findByName(searchKey) {
	console.log('findByName: ' + searchKey);
	$.ajax({
		type: 'GET',
		url: rootURL + '/search/' + searchKey,
		dataType: "json",
		success: renderList 
	});
}

function findById(id) {
	console.log('findById: ' + id);
	$.ajax({
		type: 'GET',
		url: rootURL + '/' + id,
		dataType: "json",
		success: function(data){
			$('#btnDelete').show();
			console.log('findById success: ' + data.name);
			currentEmployee = data;
			renderDetails(currentEmployee);
		}
	});
}

function addEmployee() {
	console.log('add Employee');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL +"/add",
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('Employee created successfully');
			$('#employeeId').val(data.id);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('addEmplyee error: ' + textStatus);
		}
	});
}

function updateEmployee() {
	console.log('updateEmployee');
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: rootURL + '/' + $('#employeeId').val(),
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('Employee updated successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('updateEmployee error: ' + textStatus);
		}
	});
}

function deleteEmployee() {
	console.log('deleteEmployee');
	$.ajax({
		type: 'DELETE',
		url: rootURL + '/' + $('#employeeId').val(),
		success: function(data, textStatus, jqXHR){
			alert('Employee deleted successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('deleteEmployee error');
		}
	});
}

function renderList(data) {
	var list = data;
	var tableElements = '';
	if(data.employee.length != undefined && data.employee.length > 1){
		$.each(list.employee, function(index, emp) {	
			tableElements += '<tr><td></td><td>' + emp.name + '</td><td>' + 
			emp.address + '</td><td>' + emp.contactNo + '</td><td>' + 
			emp.joinedDate + '</td><td>' + 
			emp.employmentType+ 
			'</td></tr>';
		});
	}
	else{
		tableElements += '<tr><td></td><td>' + list.employee.name + '</td><td>' + 
		list.employee.address + '</td><td>' + list.employee.contactNo + '</td><td>' + 
		list.employee.joinedDate + '</td><td>' + 
		list.employee.employmentType+ 
		'</td></tr>';
	}	
	$('#tablesorter').append(tableElements);
}

function editForm(id){
	var par = $(this).parent().parent(); 
	var name = par.children("td:nth-child(1)"); 
	var address = par.children("td:nth-child(2)"); 
	var contactNo = par.children("td:nth-child(3)"); 
	var joinedDate = par.children("td:nth-child(4)");
	var employmentType = par.children("td:nth-child(5)");
	
	name.html("<input type='text' id='txtName' value='"+name.html()+"'/>"); 
	address.html("<input type='text' id='txtPhone' value='"+address.html()+"'/>"); 
	contactNo.html("<input type='text' id='txtEmail' value='"+contactNo.html()+"'/>");
	joinedDate.html("<input type='text' id='txtDate' value='"+joinedDate.html()+"'/>"); 
	employmentType.html("<input type='text' id='txtEmplType' value='"+employmentType.html()+"'/>");
}

function renderDetails(employee) {
	$('#employeeId').val(employee.id);
	$('#name').val(employee.name);
	$('#address').val(employee.address);
	$('#contactNo').val(employee.contactNo);
	$('#joinedDate').val(employee.joinedDate);
	$('#employmentType').val(employee.employmentType);
}

// Helper function to serialize all the form fields into a JSON string
function formToJSON() {
	var employeeId = $('#employeeId').val();
	return JSON.stringify({
		"id": employeeId == "" ? null : employeeId, 
		"name": $('#name').val(), 
		"address": $('#address').val(),
		"contactNo": $('#contactNo').val(),
		"joinedDate": $('#joinedDate').val(),
		"employmentType": $('#employmentType').val()
		});
}
