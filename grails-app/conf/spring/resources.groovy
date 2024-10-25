beans = {
    System.setProperty('org.hibernate.envers.audit_table_prefix', 'AUDITED_')
    System.setProperty('org.hibernate.envers.audit_table_suffix', '')
}

beans = {
    localeResolver(org.springframework.web.servlet.i18n.SessionLocaleResolver) {
        defaultLocale = new Locale("am","AM")
        java.util.Locale.setDefault(defaultLocale)
    }
}