module Ridge {
    requires static lombok;
    requires json.simple;

    exports me.djtpj.files;
    exports me.djtpj.files.config;
    exports me.djtpj.files.config._default;
    exports me.djtpj.logger;
}