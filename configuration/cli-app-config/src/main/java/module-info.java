module mt.everest.np.config {
    exports billing.config;
    requires transitive mt.everest.np.usecase;
    requires mt.everest.np.adapters;
}