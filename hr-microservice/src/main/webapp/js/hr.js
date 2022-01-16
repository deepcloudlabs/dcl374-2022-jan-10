class Employee {
    constructor() {
        this.birthYear = ko.observable(2000)
                           .extend({ required: true, max: 2004});
        this.department = ko.observable("IT");
        this.fullname = ko.observable().extend({
            required: true, minLength : 6
        });
        this.fulltime = ko.observable(true);
        this.iban = ko.observable("TR").extend({
            required: true, iban: true
        });
        this.identity = ko.observable("1").extend({
            required: true, tcKimlikNo: true
        });;
        this.photo =
            ko.observable(AppConfig.NO_IMAGE);
        this.salary = ko.observable(2700).extend({
            required: true, min: 2700
        });
    }

    validateModel = () => {
        for (let field in this){
            let value = this[field];
            if (ko.isObservable(value) && 'rules' in value){
                value.isModified(true);
                ko.validation.validateObservable(value);
            }
        }
    }

    isModelValid = () => {
        for (let field in this){
            let value = this[field];
            if (ko.isObservable(value)
                && 'rules' in value
                && !value.isValid())
                return false;
        }
        return true;
    }

    update = (emp) => {
        for (let field in emp) {
            let value = emp[field];
            if (field in this) {
                if (ko.isObservable(this[field]))
                    this[field](value);
                else this[field] = value;
            }
        }
        if (this.photo() == null)
            this.photo(AppConfig.NO_IMAGE);
    }
}

class HrViewModel {
    constructor() {
        this.employee = new Employee();
        this.employees = ko.observableArray([]);
        this.fileData = ko.observable({
            dataUrl: ko.observable(AppConfig.NO_IMAGE)
        });
    }
    changeLngEn = () => this.changeLng('en');
    changeLngTr = () => this.changeLng('tr');
    changeLng = (lng) => {
        i18n.setLng(lng , t => {
            this.i18n();
            knockoutLocalize(lng);
            this.employee.validateModel();
        })
    }
    i18n = () => {
        $(document).i18n();
    }
    addEmployee = () => {
        let emp = ko.toJS(this.employee);
        emp.photo = toRawImage(this.fileData().dataUrl());
        fetch(AppConfig.REST_API_BASE_URL + "/employees", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(emp)
        }).then(res => res.json())
            .then(emp => toastr.success("Employee is added"));

    }
    copyEmployee = (emp) => {
        this.employee.update(emp);
        this.fileData().dataUrl(emp.photo);
    }
    updateEmployee = () => {
        let emp = ko.toJS(this.employee);
        emp.photo = toRawImage(this.fileData().dataUrl());
        fetch(AppConfig.REST_API_BASE_URL + "/employees/" + this.employee.identity(), {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(emp)
        }).then(res => res.json())
            .then(emp => toastr.success("Employee is updated"));

    }
    removeEmpAtRow = (emp) => {
        fetch(AppConfig.REST_API_BASE_URL + "/employees/" + emp.identity, {
            method: "DELETE"
        }).then(res => res.json())
            .then(employee => {
                toastr.success("Employee is deleted");
                if (employee.photo == null)
                    employee.photo = AppConfig.NO_IMAGE;
                this.employee.update(employee);
                this.fileData().dataUrl(toSrcImage(employee.photo));
                let updatedEmps =
                    this.employees()
                        .filter(e => e.identity != emp.identity);
                this.employees(updatedEmps);
            });
    }
    removeEmployee = () => {
        fetch(AppConfig.REST_API_BASE_URL + "/employees/" + this.employee.identity(), {
            method: "DELETE"
        }).then(res => res.json())
            .then(emp => {
                toastr.success("Employee is deleted");
                if (emp.photo == null)
                    emp.photo = AppConfig.NO_IMAGE;
                this.employee.update(emp);
                this.fileData().dataUrl(toSrcImage(emp.photo));
            });
    }

    findAllEmps = () => {
        fetch(`${AppConfig.REST_API_BASE_URL}/employees?page=0&size=25`)
            .then(res => res.json())
            .then(emps => {
                emps.forEach(emp => emp.photo = (emp.photo == null) ? AppConfig.NO_IMAGE : toSrcImage(emp.photo))
                this.employees(emps);
                this.i18n();
            });
    }

    findEmployee = () => {
        fetch(`${AppConfig.REST_API_BASE_URL}/employees/${this.employee.identity()}`)
            .then(res => res.json())
            .then(emp => {
                    this.employee.update(emp);
                    this.fileData().dataUrl(toSrcImage(emp.photo));
                }
            );
    }

    insertFile = (e, data) => {
        e.preventDefault();
        let files = e.target.files || e.originalEvent.dataTransfer.files;
        let reader = new FileReader();
        reader.readAsDataURL(files[0]);
        reader.onload = event => this.fileData().dataUrl(event.target.result);
    };

    dragover = e => e.preventDefault();

};