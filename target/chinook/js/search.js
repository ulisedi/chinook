// Sistema de búsqueda en tiempo real
class SearchManager {
    constructor() {
        this.searchInput = null;
        this.tableBody = null;
        this.originalRows = [];
        this.sortOrder = {};
        this.sortSelect = null;
    }

    init(searchInputId, tableBodyId, sortSelectId = null) {
        this.searchInput = document.getElementById(searchInputId);
        this.tableBody = document.getElementById(tableBodyId);
        this.sortSelect = sortSelectId ? document.getElementById(sortSelectId) : null;

        if (!this.searchInput || !this.tableBody) {
            return false;
        }

        // Guardar las filas originales
        this.originalRows = Array.from(this.tableBody.querySelectorAll('tr'));

        // Agregar evento de búsqueda
        this.searchInput.addEventListener('input', (e) => this.filterAndSort(e.target.value));
        this.searchInput.addEventListener('keyup', (e) => this.filterAndSort(e.target.value));

        // Agregar eventos de ordenamiento a los headers
        this.addSortListeners();

        // Agregar evento del dropdown de ordenamiento
        if (this.sortSelect) {
            this.sortSelect.addEventListener('change', (e) => this.handleSortSelectChange(e.target.value));
        }

        return true;
    }

    handleSortSelectChange(sortValue) {
        if (!sortValue) return;

        const [column, direction] = sortValue.split('-');
        this.sortByColumn(column, direction);
    }

    sortByColumn(column, direction) {
        const columnIndex = this.getColumnIndex(column);
        if (columnIndex === -1) return;

        const visibleRows = this.originalRows.filter(row => row.style.display !== 'none');
        
        visibleRows.sort((a, b) => {
            const aValue = a.children[columnIndex].textContent.trim();
            const bValue = b.children[columnIndex].textContent.trim();

            const aNum = parseFloat(aValue);
            const bNum = parseFloat(bValue);

            let comparison = 0;
            if (!isNaN(aNum) && !isNaN(bNum)) {
                comparison = aNum - bNum;
            } else {
                comparison = aValue.localeCompare(bValue, 'es');
            }

            return direction === 'asc' ? comparison : -comparison;
        });

        // Reorganizar el DOM
        visibleRows.forEach(row => {
            this.tableBody.appendChild(row);
        });
    }

    getColumnIndex(columnName) {
        const headers = document.querySelectorAll('th[data-sortable]');
        const mappings = {
            'id': ['id'],
            'nombre': ['nombre', 'name'],
            'name': ['nombre', 'name'],
            'título': ['título', 'title'],
            'title': ['título', 'title'],
            'artista': ['artista', 'artist'],
            'artist': ['artista', 'artist']
        };
        
        const normalizedColumn = columnName.toLowerCase();
        const equivalents = mappings[normalizedColumn] || [normalizedColumn];
        
        for (let i = 0; i < headers.length; i++) {
            const headerText = headers[i].textContent.trim().replace(/ ↑| ↓/g, '').toLowerCase();
            for (let equiv of equivalents) {
                if (headerText.includes(equiv)) {
                    return i;
                }
            }
        }
        return -1;
    }

    addSortListeners() {
        const headers = document.querySelectorAll('th[data-sortable]');
        headers.forEach(header => {
            header.style.cursor = 'pointer';
            header.addEventListener('click', (e) => this.sortTable(e.target));
        });
    }

    sortTable(header) {
        const columnIndex = Array.from(header.parentNode.children).indexOf(header);
        const columnName = header.textContent.trim().replace(/ ↑| ↓/g, '');
        
        // Determinar dirección de ordenamiento
        if (!this.sortOrder[columnName]) {
            this.sortOrder[columnName] = 'asc';
        } else if (this.sortOrder[columnName] === 'asc') {
            this.sortOrder[columnName] = 'desc';
        } else {
            this.sortOrder[columnName] = 'asc';
        }

        // Limpiar indicadores de orden
        document.querySelectorAll('th[data-sortable]').forEach(th => {
            th.innerHTML = th.innerHTML.replace(/ ↑| ↓/g, '');
        });

        // Agregar indicador de orden
        const arrow = this.sortOrder[columnName] === 'asc' ? ' ↑' : ' ↓';
        header.innerHTML += arrow;

        // Ordenar filas
        const visibleRows = this.originalRows.filter(row => row.style.display !== 'none');
        
        visibleRows.sort((a, b) => {
            const aValue = a.children[columnIndex].textContent.trim();
            const bValue = b.children[columnIndex].textContent.trim();

            // Intentar convertir a números
            const aNum = parseFloat(aValue);
            const bNum = parseFloat(bValue);

            let comparison = 0;
            if (!isNaN(aNum) && !isNaN(bNum)) {
                comparison = aNum - bNum;
            } else {
                comparison = aValue.localeCompare(bValue, 'es');
            }

            return this.sortOrder[columnName] === 'asc' ? comparison : -comparison;
        });

        // Reorganizar el DOM
        visibleRows.forEach(row => {
            this.tableBody.appendChild(row);
        });
    }

    filterAndSort(searchTerm) {
        const term = searchTerm.toLowerCase().trim();

        if (term === '') {
            // Mostrar todas las filas
            this.originalRows.forEach(row => {
                row.style.display = '';
            });
            this.removeNoResults();
            return;
        }

        let visibleCount = 0;

        this.originalRows.forEach(row => {
            const text = row.textContent.toLowerCase();
            if (text.includes(term)) {
                row.style.display = '';
                visibleCount++;
            } else {
                row.style.display = 'none';
            }
        });

        // Si no hay resultados, mostrar mensaje
        if (visibleCount === 0) {
            this.showNoResults();
        } else {
            this.removeNoResults();
        }
    }

    showNoResults() {
        // Verificar si ya existe el mensaje
        if (document.getElementById('no-results-message')) {
            return;
        }

        const noResultsRow = document.createElement('tr');
        noResultsRow.id = 'no-results-message';
        noResultsRow.innerHTML = `
            <td colspan="100%" style="text-align: center; padding: 20px; color: #999; font-style: italic;">
                No se encontraron resultados
            </td>
        `;
        this.tableBody.appendChild(noResultsRow);
    }

    removeNoResults() {
        const noResultsRow = document.getElementById('no-results-message');
        if (noResultsRow) {
            noResultsRow.remove();
        }
    }

    clear() {
        if (this.searchInput) {
            this.searchInput.value = '';
            this.filterAndSort('');
        }
    }
}

// Instancia global
const searchManager = new SearchManager();

// Inicializar búsqueda cuando el DOM esté listo
document.addEventListener('DOMContentLoaded', function() {
    // Intentar inicializar búsqueda en diferentes tablas
    const searchConfigs = [
        { inputId: 'search-input', bodyId: 'table-body', sortId: 'sort-select' },
        { inputId: 'artist-search', bodyId: 'artist-table-body', sortId: 'sort-select' },
        { inputId: 'genre-search', bodyId: 'genre-table-body', sortId: 'sort-select' },
        { inputId: 'album-search', bodyId: 'album-table-body', sortId: 'sort-select' },
        { inputId: 'playlist-search', bodyId: 'playlist-table-body', sortId: 'sort-select' }
    ];

    for (let config of searchConfigs) {
        if (searchManager.init(config.inputId, config.bodyId, config.sortId)) {
            console.log(`Search initialized for ${config.inputId}`);
            break;
        }
    }
});
