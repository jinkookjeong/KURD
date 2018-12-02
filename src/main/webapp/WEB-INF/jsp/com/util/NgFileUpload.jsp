<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>

    <!-- Fine Uploader New/Modern CSS file
    ====================================================================== -->
    <link href="/js/fine-uploader/fine-uploader-new.css" rel="stylesheet">

    <!-- Fine Uploader JS file
    ====================================================================== -->
    <script src="/js/fine-uploader/fine-uploader.js"></script>

    <!-- Fine Uploader Thumbnails template w/ customization
    ====================================================================== -->
    <script type="text/template" id="qq-template-manual-trigger">
	 
        <div class="qq-uploader-selector qq-uploader" qq-drop-area-text="Drop files here!">
            <div class="qq-total-progress-bar-container-selector qq-total-progress-bar-container">
                <div role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" class="qq-total-progress-bar-selector qq-progress-bar qq-total-progress-bar"></div>
            </div>
            <div class="qq-upload-drop-area-selector qq-upload-drop-area" qq-hide-dropzone>
                <span class="qq-upload-drop-area-text-selector"></span>
            </div>
            <div class="buttons">
                <div class="qq-upload-button-selector qq-upload-button">
                    <div>Select files...</div>
                </div>
                <!-- button type="button" id="trigger-upload" class="btn btn-primary">
                    <i class="icon-upload icon-white"></i> Upload
                </button -->
				<!-- <input type="hidden" id="trigger-upload" class="btn btn-primary">
                    <i class="icon-upload icon-white"></i>
                </input> -->

            </div>
            <span class="qq-drop-processing-selector qq-drop-processing">
                <span>Processing dropped files...</span>
                <span class="qq-drop-processing-spinner-selector qq-drop-processing-spinner"></span>
            </span>
            <ul class="qq-upload-list-selector qq-upload-list" aria-live="polite" aria-relevant="additions removals">
                <li>
                    <div class="qq-progress-bar-container-selector">
                        <div role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" class="qq-progress-bar-selector qq-progress-bar"></div>
                    </div>
                    <span class="qq-upload-spinner-selector qq-upload-spinner"></span>
                    <img class="qq-thumbnail-selector" qq-max-size="25" qq-server-scale>
                    <span class="qq-upload-file-selector qq-upload-file"></span>
                    <span class="qq-edit-filename-icon-selector qq-edit-filename-icon" aria-label="Edit filename"></span>
                    <input class="qq-edit-filename-selector qq-edit-filename" tabindex="0" type="text">
                    <span class="qq-upload-size-selector qq-upload-size"></span>
                    <button type="button" class="qq-btn qq-upload-cancel-selector qq-upload-cancel">Cancel</button>
                    <button type="button" class="qq-btn qq-upload-retry-selector qq-upload-retry">Retry</button>
                    <button type="button" class="qq-btn qq-upload-delete-selector qq-upload-delete">Delete</button>
                    <span role="status" class="qq-upload-status-text-selector qq-upload-status-text"></span>

        			<!-- <button type="button" class="view-btn hide btn">View</button> -->
			      </li>

            </ul>

            <dialog class="qq-alert-dialog-selector">
                <div class="qq-dialog-message-selector"></div>
                <div class="qq-dialog-buttons">
                    <button type="button" class="qq-cancel-button-selector">Close</button>
                </div>
            </dialog>

            <dialog class="qq-confirm-dialog-selector">
                <div class="qq-dialog-message-selector"></div>
                <div class="qq-dialog-buttons">
                    <button type="button" class="qq-cancel-button-selector">No</button>
                    <button type="button" class="qq-ok-button-selector">Yes</button>
                </div>
            </dialog>

            <dialog class="qq-prompt-dialog-selector">
                <div class="qq-dialog-message-selector"></div>
                <input type="text">
                <div class="qq-dialog-buttons">
                    <button type="button" class="qq-cancel-button-selector">Cancel</button>
                    <button type="button" class="qq-ok-button-selector">Ok</button>
                </div>
            </dialog>
        </div>
    </script>

    <style>
        #trigger-upload {
            color: white;
            background-color: #00ABC7;
            font-size: 14px; 
            padding: 7px 20px;
            background-image: none;
        }

        #fine-uploader-manual-trigger .qq-upload-button {
            margin-right: 15px;
        }

        #fine-uploader-manual-trigger .buttons {
            width: 36%;
        }

        #fine-uploader-manual-trigger .qq-uploader .qq-total-progress-bar-container {
            width: 60%;
        }
    </style>
   
    <title>Fine Uploader Manual Upload Trigger Demo</title>
</head>

<body>
     <!-- Fine Uploader DOM Element
    ====================================================================== -->
    <div id="fine-uploader-manual-trigger"></div> 
    <!-- Your code to create an instance of Fine Uploader and bind to the DOM/template
    ====================================================================== -->
    <script>
//     var token = $("meta[name='_csrf']").attr("content");
//     var header = $("meta[name='_csrf_header']").attr("content");
    
      var signature = "signature";
        var manualUploader = new qq.FineUploader({
        	
            element: document.getElementById('fine-uploader-manual-trigger'),
            template: 'qq-template-manual-trigger',   
            session: {
            	endpoint: '/pt/ng/rest/upload?userId=${param.userId}&pageId=${param.pageId}&fileRefId=${param.fileRefId}&init=${param.init}',
            	customHeaders: {
            		'${_csrf.headerName}' : '${_csrf.token}'
            	 }
            },
            request: {
                //endpoint: '/pt/ng/rest/upload?userId=${param.userId}&pageId=${param.pageId}&fileRefId=${param.fileRefId}&signature='+signature
         		endpoint: '',
         		 customHeaders: {
         			'${_csrf.headerName}' : '${_csrf.token}'
              	 }
            },
            thumbnails: {
                placeholders: {
                    waitingPath: '/js/fine-uploader/placeholders/waiting-generic.png',
                    notAvailablePath: '/js/fine-uploader/placeholders/not_available-generic.png'
                }
            },
            validation: {
                 //allowedExtensions: ['jpeg', 'jpg','png','bmp', 'gif', 'txt','ppt','pptx','xls','xlsx','pdf','doc','docx','zip'],
                itemLimit: 10,
                 //sizeLimit: 51200 // 50 kB = 50 * 1024 bytes
            },
             deleteFile: {
             	 enabled: true,
                  method: "DELETE",
                  forceConfirm: true,
                  endpoint: "/pt/ng/rest/upload?userId=${param.userId}&pageId=${param.pageId}&realDel=${param.realDel}&fileRefId=${param.fileRefId}",
                  customHeaders: {
           			'${_csrf.headerName}' : '${_csrf.token}'
                	 }
             },
             callbacks: {
				  onSubmit: function(id, fileName) {
					  //var file = this.getFile(id);
					  //console.log(file.name);
					  //File Reader
					  //var reader = new FileReader();
					  //reader.onload = function(e) {
					  //	var content = reader.result;					
					  //	console.log(content);
					  //}
					  //reader.readAsText(file);
					  
					  var signature = "w8LJT9FEJmhTT0eYN2pZXGHTe4A%3D";
					  var endPointUrl = "/pt/ng/rest/upload?userId=${param.userId}&pageId=${param.pageId}&fileRefId=${param.fileRefId}&signature="+signature;
					  this.setEndpoint(endPointUrl, id);
				  },
                 onStatusChange :function(id, oldStatus, newStatus) { //inital getsession callback function    
                	 
                    if(newStatus === "upload successful"){                    	
	             		var fileInfo = new Object();
	                    fileInfo.fileUuid = this.getUuid(id);                         
	                    fileArray.push(fileInfo);
                    }
                    if(newStatus === "deleted"){                    	
                    	for (var i = 0; i < fileArray.length; i++) { //e°?i? uuide°? i?­i??
   					     if(fileArray[i].fileUuid === this.getUuid(id)) {					    	 
   						    fileArray.splice(i,1);   						    
   	                 	 }
                    	}
                    }
               },
             },
            autoUpload: true,
            debug: true
        });
//          qq(document.getElementById("trigger-upload")).attach("click", function() {
//              var ss = manualUploader.uploadStoredFiles();            
//          });
    </script>
    
   
</body>
</html>