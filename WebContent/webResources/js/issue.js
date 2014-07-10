function sendColumnName(status) {
	document.columnName.sortColumn.value=status;
	document.columnName.submit();
}

function sendId(id) {
	document.idNumber.id.value=id;
	document.idNumber.submit();
}
function editProject(id) {
	document.idProject.projectId.value=id;
	document.idProject.submit();
}
function changeProjectPage (page) {
	document.numberPage.page.value = page;
	document.numberPage.submit();
}

function nextProjectPage (page) {
	
	document.nextPage.page.value = ++page;
	document.nextPage.submit();
}
function backProjectPage (page) {
	document.backPage.page.value = --page;
	document.backPage.submit();
}
function addName (name, id) {
	
	document.name.setName.value=name;
	document.name.setId.value=id;
	document.name.submit();
	
}

function loadingFile(fileIdentifier,load) {
	if(load == "upload"){
		document.uploadForm.id.value=fileIdentifier;
		document.uploadForm.submit();}
	
	if(load == "download"){
		document.downloadForm.fileName.value=fileIdentifier;
		document.downloadForm.submit();}
}

function chngUserData(){
	
	var select;
	select= document.getElementById('select');
	
	document.chngData.email_employee.value= select.options[select.selectedIndex].text;
	
	document.chngData.submit();
}