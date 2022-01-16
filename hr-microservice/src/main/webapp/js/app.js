let hrViewModel= new HrViewModel();
$(
    () => {
        i18n.init(AppConfig.I18N_CONFIG, t => {
           toastr.options = AppConfig.TOASTR_CONFIG;
           knockoutLocalize('en');
           ko.applyBindings(hrViewModel);
           hrViewModel.employee.validateModel();
        })
    }
);